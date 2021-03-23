package com.dj.songs.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author dengjie09
 * @description:
 * @date：2020/4/15 8:53 PM
 *
 */
class UserModel : ViewModel() {

    val mUserLiveData = MutableLiveData<String>()

     init {
        //模拟从网络加载用户信息
        mUserLiveData.postValue("ddddd")
    }

    //模拟 进行一些数据骚操作
    fun doSomething() {
        var user = mUserLiveData.getValue();
        if (user != null) {
            user = "name15";
            mUserLiveData.setValue(user);
        }
    }


}