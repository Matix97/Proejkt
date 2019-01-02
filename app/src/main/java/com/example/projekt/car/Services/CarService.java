package com.example.projekt.car.Services;

import com.example.projekt.car.DTOs.Cars;

import com.example.projekt.car.DTOs.Fault;
import com.example.projekt.car.DTOs.Fuel;
import com.example.projekt.car.DTOs.TakeCar;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CarService {

    @GET("cars")
    Call<List<Cars>> getCars();
    @POST("cars/takings")
    Call<ResponseBody> takeCar(@Body TakeCar takeCar);
    @POST("fuelings")
    Call<ResponseBody> fuel(@Body Fuel fuel);
    @POST("faults")
    Call<ResponseBody> reportFault(@Body Fault fault );

}
