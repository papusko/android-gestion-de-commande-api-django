package com.africa.cloud.commandes.service;

import com.africa.cloud.commandes.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitHubClient {

    @GET("users")
    Call<List<User>> reposForUser();



    @POST("auth/register/")
    @FormUrlEncoded
    Call<User> saveUser(@Field("prenom") String prenom,
                        @Field("nom") String nom,
                        @Field("username") String username,
                        @Field("email") String email,
                        @Field("password") String password,
                        @Field("confirmPassword") String confirmPassword);





    @POST("auth/login/")
    @FormUrlEncoded
    Call<User> refreshToken(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
    }



