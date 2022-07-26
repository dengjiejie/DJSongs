package com.dj.songs.jank

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dj.songs.R
import com.dj.songs.ShellUtils

/**
 *  author : dengjiejie
 *  date : 2022/7/26 11:13 上午
 *  description : 卡顿，cpu 监听，
 */
class JankAndCpuActivity : AppCompatActivity() {

    private var contentShow : TextView? = null
    private val cpuTracker = CPUTracker()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break_jank_cpu)
    }

    override fun onResume() {
        super.onResume()


        contentShow = findViewById(R.id.context_text)
        findViewById<View>(R.id.cpu_test).setOnClickListener {
            contentShow?.text = ShellUtils.Companion.execCommand("ls", false).toString()
//            contentShow?.text = cpuTracker.getProcessCpuRateAdb()
        }

        findViewById<View>(R.id.jank_test).setOnClickListener {


        }

    }




}