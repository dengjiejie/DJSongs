package com.dj.songs.image.imageloader.loader

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * @author dengjie09
 * @description:
 * @date：2020/4/2 7:20 PM
 *
 */
class LoaderImage// 50m
private constructor(context: Context) {

    var lruCache : LruCache<String, Bitmap>? = null
    var diskLruCache : DiskLruCache? = null
    var mIsDiskLruCacheCreated = false

    private val CPU_COUNT= Runtime.getRuntime().availableProcessors()
    private val sf: ThreadFactory = object : ThreadFactory{
        val count = AtomicInteger(1)
        override fun newThread(r: Runnable): Thread {
            return Thread(r, "haha" + count.getAndIncrement())
        }
    }
    private val POOL = ThreadPoolExecutor(
        CPU_COUNT + 1,
        CPU_COUNT * 2 + 1,
        10,
        TimeUnit.SECONDS,
        LinkedBlockingDeque<Runnable>(),
        sf
    )

    init {
        val cacheize = ((Runtime.getRuntime().maxMemory()/ 1024) / 8 ).toInt()
        lruCache = object :LruCache<String, Bitmap>(cacheize) {
            override fun sizeOf(key: String?, bitmap: Bitmap): Int {
                return bitmap.rowBytes * bitmap.height / 1024
            }
        }
        val file = getDiskCacheDir(context, "bitmap")
        if (!file.exists()) {
            file.mkdirs()
        }
        if(getUseable(file) > 50 * 1024 * 1024) {
            try {
                diskLruCache = DiskLruCache.open(file, 1, 1,50 * 1024 * 1024)
                mIsDiskLruCacheCreated = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    val handler = object : Handler(Looper.getMainLooper()){
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
            val loaderResult = msg.obj as LoaderResult
            val url = loaderResult.imageView.tag as String
            if (loaderResult.uri == url) {
                loaderResult.imageView.setImageBitmap(loaderResult.bitmap)
            } else {
                Log.w(
                    "haha",
                    "set image bitmap,but url has changed, ignored!"
                )
            }
        }
    }

    companion object {
        fun build(context: Context): LoaderImage {
            return LoaderImage(context)
        }

    }

    fun bindBitmap(url: String, imageView: ImageView) {
        bindBitmap(url, imageView, 0, 0)

    }

    fun bindBitmap(url: String, imageView: ImageView, width: Int, height: Int) {

        imageView.tag = url

        var bitmap = loadBitmapFromMemCacheUrl(url)

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            return
        }

        POOL.execute {
            bitmap = loaderBitmap(url, width, height)

            if (bitmap != null) {
                val loaderResult = LoaderResult(imageView, url, bitmap)
                handler.obtainMessage(1, loaderResult).sendToTarget()
            }
        }
    }
    private class LoaderResult(
        var imageView: ImageView,
        var uri: String,
        var bitmap: Bitmap?
    )

    fun getDiskCacheDir(context: Context, fileName:String) : File {
        val externalStorageState  = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        val cachePath: String?
        if (externalStorageState) {
            cachePath = context.externalCacheDir?.path
        } else {
            cachePath = context.cacheDir.path
        }
        return File(cachePath + File.separator + fileName)
    }

    fun getUseable(file: File): Long {
        return file.usableSpace
    }

    fun loaderBitmap(url: String, width: Int, height: Int) : Bitmap? {
        var bitmap = loadBitmapFromMemCacheUrl(url)
        if (bitmap != null) {
            return bitmap
        }

        bitmap = loadBitmapFromDiskCacheUrl(url, width, height)

        if (bitmap != null) {
            return bitmap
        }

        bitmap = loadBitmapFromHttp(url, width, height)
        if (bitmap != null) {
            return bitmap
        }

        if (!mIsDiskLruCacheCreated) {
            bitmap = loadBitmapUrl(url)
        }

        return bitmap
    }

    private fun loadBitmapUrl(urlString: String): Bitmap {
        val url = URL(urlString)
        val con = url.openConnection() as HttpURLConnection
        val inputStream = BufferedInputStream(con.inputStream, 8 * 1024)
        return BitmapFactory.decodeStream(inputStream)
    }


    private fun loadBitmapFromHttp(url: String, width: Int, height: Int): Bitmap? {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("can not visit network from UI Thread.")
        }
        if (diskLruCache == null) {
            return null
        }
        val key = getHashKey(url)
        val editor = diskLruCache?.edit(key)
        if (editor != null) {
            val output = editor.newOutputStream(0)
            if (downloadUrlToStream(url, output)) {
                editor.commit()
            } else {
                editor.abort()
            }
            diskLruCache?.flush()
        }

        return loadBitmapFromDiskCacheUrl(url, width, height)

    }

    private fun downloadUrlToStream(urlString : String, outputStream : OutputStream?) : Boolean {

        var connetion: HttpURLConnection? = null
        var input: BufferedInputStream? = null
        var output: BufferedOutputStream? = null

        try {
            val url = URL(urlString)
            connetion = url.openConnection() as HttpURLConnection
            input = BufferedInputStream(connetion.inputStream, 8 * 1024)
            output = BufferedOutputStream(outputStream, 8 * 1024)
            var b: Int
            while((input.read().also { b = it })  != -1) {
                output.write(b)
            }
            return true
        } catch (e : Exception) {

        } finally {
            connetion?.disconnect()
            input?.close()
            output?.close()
        }
        return false
    }

    private fun loadBitmapFromDiskCacheUrl(url: String, width: Int, height: Int): Bitmap? {

        if (Looper.myLooper() == Looper.getMainLooper()) {
        }
        if (diskLruCache == null) {
            return null
        }

        val key = getHashKey(url)
        val snapshot = diskLruCache?.get(key)
        if (snapshot != null) {
            val input = snapshot.getInputStream(0) as FileInputStream
            val fileDescriPtor = input.fd
            val bitmap = ImageResizer().decodeBitmapFromInputStream(fileDescriPtor, width, height)

            if (bitmap != null) {
                addMemeryCache(key, bitmap)
            }

            return bitmap
        }
        return null
    }



    private fun loadBitmapFromMemCacheUrl(url: String): Bitmap? {
        return getBitmapFromMemCache(getHashKey(url))
    }

    fun addMemeryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            lruCache?.put(key, bitmap)
        }
    }

    private fun getBitmapFromMemCache(key: String): Bitmap? {
        return lruCache?.get(key);
    }

    private fun getHashKey(url: String): String {
        var key: String = url.hashCode().toString()
        key = try {
            val mssageDigest = MessageDigest.getInstance("MD5")
            mssageDigest.update(url.toByteArray())
            bytesToHexString(mssageDigest.digest())
        } catch (e: NoSuchAlgorithmException) {
            url.hashCode().toString()
        }
        return key
    }

    private fun bytesToHexString(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }




}