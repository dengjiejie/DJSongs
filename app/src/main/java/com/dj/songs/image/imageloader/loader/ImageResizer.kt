package com.dj.songs.image.imageloader.loader

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileDescriptor

/**
 *
 * @author dengjie09
 * @description:
 * @date：2020/4/2 7:32 PM
 *
 */
class ImageResizer {

    fun decodeBitmapFromSource(res: Resources, resId: Int, width: Int, height: Int) : Bitmap? {

        var option = BitmapFactory.Options()
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, option)
        option.inSampleSize = calInSampleSize(option, width, height)
        option.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, option)
    }

    fun decodeBitmapFromInputStream(fileDescriptor : FileDescriptor, width: Int, height: Int) : Bitmap? {
        var option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, option)
        option.inSampleSize = calInSampleSize(option, width, height)
        option.inJustDecodeBounds = false
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, option)
    }

    fun calInSampleSize(option: BitmapFactory.Options, width: Int, height: Int): Int {
        if (width == 0 || height == 0) {
            return 1
        }

        var h = option.outHeight
        var w = option.outHeight

        var result = 1;

        while(h / result >= height && w / result >= width) {
            result *= 2;
        }

        return result / 2;

    }




}