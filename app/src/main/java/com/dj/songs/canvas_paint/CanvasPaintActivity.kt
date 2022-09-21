package com.dj.songs.canvas_paint

import android.app.Activity
import android.os.Build
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.dj.songs.BaseActivity
import com.dj.songs.R

/**
 * author : dengjiejie
 * date : 2020/8/5 10:44 AM
 * description :
 */
class CanvasPaintActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_paint)
    }

    override fun onResume() {
        super.onResume()
        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.la_aw)
        lottieAnimationView.playAnimation()
        isFreeFormWindowMode(this)
    }

    private fun isFreeFormWindowMode(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 29) {
                val clazz: Class<*> = activity.javaClass
                val method = clazz.getMethod("getWindowingMode")
                method.isAccessible = true
                val windowMode = method.invoke(activity, null) as Int
                //WindowConfiguration#WINDOWING_MODE_FREEFORM
                val WINDOWING_MODE_FREEFORM = 5 //多窗口模式
                return windowMode == WINDOWING_MODE_FREEFORM

        }
        return false
    }
}