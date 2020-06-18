package com.dj.songs.network.net;

import java.util.Observer;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/8 10:50 PM
 */
public interface GithubApiService {

    @GET ("users/{name}/repos")
    Observable<ResponseBody> search(@Path("name") String name);


}
