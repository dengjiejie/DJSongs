package com.dj.songs.viewpager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.dj.songs.R

/**
 *  author : dengjiejie
 *  date : 2020/7/9 5:35 PM
 *  description :
 */
class ViewPagerAdapter(private var ivGoodsList: List<String>?, var context: Context): PagerAdapter() {


    override fun getCount(): Int {
        return 6
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView?.setImageResource(R.drawable.page0)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)

    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        val imageView: ImageView? = `object` as ImageView?
        container.removeView(imageView)
    }

}