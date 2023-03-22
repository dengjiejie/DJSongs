package com.dj.songs.aop

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

/**
 *  author : dengjiejie
 *  date : 2022/9/21 17:48
 *  description :
 */
@Aspect
class AspectLifeCircle {


    companion object {
        const val TAG = "AspectLifeCircle"
        const val ACTIVITY_LIFECIRCLE = "execution(* com.dj.songs.BaseActivity.on**(..)) "
        const val FRAGMENT_LIFECIRCLE = "execution(* androidx.fragment.app.Fragment+.on*(..))"
    }

    @Pointcut(ACTIVITY_LIFECIRCLE)
    fun activityLifeCircle() {
    }


    @Pointcut(FRAGMENT_LIFECIRCLE)
    fun fragmentLifeCircle() {
    }

    @Before("activityLifeCircle() || fragmentLifeCircle()")
    fun lifeCircle(joinPoint: JoinPoint) {
        Log.d(TAG, "Target : " + joinPoint.target + "; Pointcut = " + joinPoint.staticPart)
    }
}