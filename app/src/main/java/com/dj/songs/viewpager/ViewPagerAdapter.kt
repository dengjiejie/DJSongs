package com.dj.songs.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 *  author : dengjiejie
 *  date : 2020/7/9 5:35 PM
 *  description :
 */
class ViewPagerAdapter: PagerAdapter() {
    private var datas: List<View>? = null

    fun ViewAdapter(list: List<View>?) {
        datas = list
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return datas!!.size
    }



}