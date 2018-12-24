package com.example.projekt.car;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.example.projekt.car.Exceptions.CarDoesNotExist;
import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;

public class ChoseCar extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_car_layout);

        String idCar=getIntent().getExtras().getString("idCar");


        TextView textViewName = this.findViewById(R.id.chose_car_textview);


        Car car=null;

        try {
            car = new CarsDataBase().getCar(idCar);
        } catch (CarDoesNotExist carDoesNotExist) {
            carDoesNotExist.printStackTrace();
        }


        textViewName.setText("CarId: "+car.getCarsID());








    }

}
