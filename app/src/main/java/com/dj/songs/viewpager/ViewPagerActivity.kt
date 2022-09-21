package com.dj.songs.viewpager

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dj.songs.BaseActivity
import com.dj.songs.R


/**
 *  author : dengjiejie
 *  date : 2020/7/9 5:32 PM
 *  description :
 */
class ViewPagerActivity: BaseActivity() {

    var viewpager : ViewPager? = null
    private var pages: List<String>? = null
    var mViewPager2 : ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        viewpager = findViewById(R.id.viewpager)
        mViewPager2 = findViewById(R.id.view_pager2)
        mViewPager2?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewpager?.adapter =  ViewPagerAdapter(pages, this)

//        mViewPager2?.adapter = ViewPager2Adapter(this)


        viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("dengjiejie", " state = " + state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                Log.d(
                    "dengjiejie",
                    " positionOffset = " + positionOffset + " positionOffsetPixels = " + positionOffsetPixels
                )

            }

            override fun onPageSelected(position: Int) {
                Log.d("dengjiejie", " position = " + position)
            }

        })

        val mAnimator = ValueAnimator.ofFloat(0f, 2400f)
            .setDuration(2400)

        mAnimator.repeatCount = 1
        mAnimator.addUpdateListener { animation: ValueAnimator ->
            val progress = animation.animatedValue.toString().toFloat()
            viewpager?.scrollTo(progress.toInt(), 0)

        }

        mAnimator.start()


    }

}