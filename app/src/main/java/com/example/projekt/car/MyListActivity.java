package com.example.projekt.car;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListActivity extends ListActivity {

    List<Cars> data = new ArrayList<>();
    List<Cars> finalData = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadCars();
    }


    void downloadCars() {
        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call = carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if (response.isSuccessful()) {

                    data = response.body();
                    for (int i = 0; i < data.size(); i++) {
                        if (!data.get(i).isTaken() && data.get(i).isOk())
                            finalData.add(data.get(i));
                    }
                    CarArturAdapter arturAdapter = new CarArturAdapter(MyListActivity.this, R.layout.cars_adapter, finalData);
                    setListAdapter(arturAdapter);
                } else
                    Toast.makeText(MyListActivity.this, "Error in GET cars ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(MyListActivity.this, "FAILURE Error in GET cars ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
