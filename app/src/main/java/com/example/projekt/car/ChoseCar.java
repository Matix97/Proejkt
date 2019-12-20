package com.example.projekt.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.projekt.car.Exceptions.CarDoesNotExist;
import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;
import com.example.projekt.car.fragments.HelloFragment;

public class ChoseCar extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_car_layout);

        String idCar = getIntent().getExtras().getString("idCar");


        TextView textViewName = this.findViewById(R.id.chose_car_textview);


       Car car = null;

        try {
            car = new CarsDataBase().getCar(idCar);
        } catch (CarDoesNotExist carDoesNotExist) {
            carDoesNotExist.printStackTrace();
        }
        Button tank =this.findViewById(R.id.tankowanie);
        Car finalCar = car;
        tank.setOnClickListener(v->{
            Intent intent = new Intent(ChoseCar.this, RentedCarActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("idCar", finalCar.getCarsID());
            bundle.putString("registrationNumber",finalCar.getCarsID());
            bundle.putString("model",finalCar.getCarsID());
            intent.putExtras(bundle);
            startActivity(intent);
        });


        textViewName.setText("CarId: " + car.getCarsID());



    }

}
