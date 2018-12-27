package com.example.projekt.car.Services;

import com.example.projekt.car.DTOs.BearerToken;
import com.example.projekt.car.DTOs.Register;
import com.example.projekt.car.data.Person;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
public interface UserService {

    @POST("users")
    Call<Person> register(@Body Register register);
    @POST("login")
    Call<BearerToken> login(@Header("Authorization") String basicAuthCredentials);

}
