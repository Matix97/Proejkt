package com.example.projekt.car.LoginAndRegister;

import com.example.projekt.car.data.Person;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
public interface UserService {

  /*  @POST("login")
    Call<Person> login(@Body Login login);*/
    @POST("users")
    Call<Person> register(@Body Register register);
    @POST("login")
    Call<String> getUser(@Header("Authorization") String uthHeader );


/*
    @GET("someInfo")
    Call<ResponseBody> getInfo(@Header("Authorization") String authToken);*/



}
