package com.apppartner.androidprogrammertest.services;

import com.apppartner.androidprogrammertest.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface AuthServices {

    @FormUrlEncoded
    @retrofit2.http.POST("login.php/")
    Call<LoginResponse> getToken(
                                 @Field("username") String username,
                                 @Field("password") String password);
}
