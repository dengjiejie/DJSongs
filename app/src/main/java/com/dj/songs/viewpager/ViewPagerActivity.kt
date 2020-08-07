package com.dj.songs.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dj.songs.R


/**
 *  author : dengjiejie
 *  date : 2020/7/9 5:32 PM
 *  description :
 */
class ViewPagerActivity: AppCompatActivity() {

    var viewpager : ViewPager? = null
    private var pages: List<String>? = null
    var mViewPager2 : ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        viewpager = findViewById(R.id.viewpager)
        mViewPager2 = findViewById(R.id.view_pager2)
        mViewPager2?.orientation = ViewPager2.ORIENTATION_VERTICAL
        mViewPager2?.adapter =  ViewPager2Adapter(pages, this)
    }

}