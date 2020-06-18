package com.dj.songs.viewmodel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dj.songs.R


/**
 *
 * @author dengjie09
 * @description:
 * @date：2020/4/15 10:05 PM
 *
 */
class FragmentA(val viewModelActivity: ViewModelActivity) : Fragment() {


    var text : TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.layout_fragment_a, container, false)
        text = root.findViewById(R.id.f_text_a)

        viewModelActivity.viewModel?.mUserLiveData?.observe(this,  Observer<String>(){
            text?.text = it
        })
        return root
    }





}