package com.dj.songs.network.net;

import com.dj.songs.network.model.IPModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/8 10:50 PM
 */
public interface IPApiService {

    @GET("getIpInfo.php")
    Call<IPModel> getIpMsg(@Query("ip") String ip);


}
