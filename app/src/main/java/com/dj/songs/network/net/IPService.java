package com.dj.songs.network.net;


import com.dj.songs.network.model.IPModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/8 4:15 PM
 */
public class IPService {


    public void request(Callback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ip.taobao.com/service/")
                .build();

        IPApiService githubService = retrofit.create(IPApiService.class);
        Call<IPModel> call = githubService.getIpMsg("59.108.54.37");
        call.enqueue(callback);

    }
}
