package com.dj.songs.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import java.util.function.Function

/**
 *  author : dengjiejie
 *  date : 2021/7/27 2:59 下午
 *  description :
 */

@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {

    var demo1 = Demo()
    val function: Function<String?, String?> = object : Function<String?, String?> {
        override fun apply(t: String?): String? {
            System.out.println(t)
            return t
        }
    }
    demo1.add(function)
    println(demo1.get<String>("Ssssss"))



    println(LVFloatUIElementConfig().mViewType)




}


class Demo {

    var list: MutableList<Function<*,*>> = ArrayList()

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun add(function: Function<*, *>) {
        list.add(function)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun <T> get(p: T ) : T {
        return ((list[0] as Function<T, T>).apply(p)) as T
    }



}

open class LVUIElementConfig(
    val mViewType: Int = 0)


class LVFloatUIElementConfig(
    mViewType: Int = 1

) : LVUIElementConfig(
    mViewType

)


