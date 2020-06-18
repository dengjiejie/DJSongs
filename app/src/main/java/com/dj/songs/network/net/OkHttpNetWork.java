package com.dj.songs.network.net;

import java.io.IOException;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/8 4:14 PM
 */
public class OkHttpNetWork {

    public String requestNet() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://www.baidu.com").build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return  response.body().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void requestNetCallBack(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        okHttpClient.newCall(request).enqueue(callback);
    }






}
