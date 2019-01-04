package com.example.projekt.car;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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
    // ListView listView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.list_activity);
        // listView=(ListView)findViewById(R.id.listOfCars);
        downloadCars();

       /* new WebServiceHandler()
                .execute();             //downloading date in background*/
    }

    void downloadCars() {
        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call = carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(MyListActivity.this, response.body().get(0).getModel(), Toast.LENGTH_SHORT).show();
                    data = response.body();
                    //deleting broken or unavailable cars
                    /*for(Cars c:data){
                        if(!c.isOK() || c.isTaken())
                            data.
                    }*/
                    CarArturAdapter arturAdapter = new CarArturAdapter(MyListActivity.this, R.layout.cars_adapter, data);
                    // listView.setAdapter(arturAdapter);
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
