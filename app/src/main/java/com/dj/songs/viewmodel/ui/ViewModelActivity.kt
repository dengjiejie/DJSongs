package com.dj.songs.viewmodel.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dj.songs.BaseActivity
import com.dj.songs.R
import com.dj.songs.viewmodel.vm.UserModel

/**
 *
 * @author dengjie09
 * @description:
 * @date：2020/4/15 8:48 PM
 *
 */
class ViewModelActivity : BaseActivity() {


    var button : Button? = null

    var textView: TextView? = null
    var viewModel: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        viewModel = ViewModelProvider(this).get(UserModel::class.java)
        viewModel?.mUserLiveData?.observe(this,  Observer<String>(){
            textView?.text = it
        })
        button?.setOnClickListener { viewModel?.doSomething() }
        val fragmentManager =  supportFragmentManager.beginTransaction()

        fragmentManager.add(R.id.fragment1, FragmentA(this))
            .add(R.id.fragment2, FragmentB(this))
            .commit()
    }
}