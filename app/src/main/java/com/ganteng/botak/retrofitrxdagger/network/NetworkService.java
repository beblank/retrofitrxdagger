package com.ganteng.botak.retrofitrxdagger.network;

import com.ganteng.botak.retrofitrxdagger.login.model.Post;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by adityahadiwijaya on 4/21/17.
 */

public interface NetworkService {

    @POST("/posts")
    @FormUrlEncoded
    Observable<Post> sendPost(
            @Field("title") String title,
            @Field("body") String body
    );

    @GET("/posts/1")
    Observable<Post> getPost();
}
