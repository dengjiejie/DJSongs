package com.dj.songs.viewpager

import android.R.drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.dj.songs.R
import java.lang.reflect.Field


/**
 *  author : dengjiejie
 *  date : 2020/7/9 5:32 PM
 *  description :
 */
class ViewPagerActivity: FragmentActivity() {

    var viewpager : ViewPager? = null

    private var pages: List<View>? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_viewpager)

        pages = getPages()
        viewpager = findViewById(R.id.viewpager)

        viewpager?.adapter = ViewPagerAdapter()



    }


    private fun getPages(): List<View>? {
        val pages: MutableList<View> = ArrayList()
        val fields: Array<Field> = drawable::class.java.declaredFields
        try {
            for (field in fields) {
                if (field.getName().startsWith("page")) {
                    val view = ImageView(this)
                    view.setImageResource(field.getInt(null))
                    view.setScaleType(ImageView.ScaleType.CENTER_CROP)
                    pages.add(view)
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return pages
    }







}