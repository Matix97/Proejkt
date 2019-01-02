package com.example.projekt.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.data.Car;

import java.util.List;

public class CarArturAdapter extends ArrayAdapter<Cars> {

    List<Cars> carList;
    Context context;
    int resource;

    public CarArturAdapter( @NonNull Context context, int resource, @NonNull List<Cars> objects) {
        super(context, resource, objects);
        this.carList = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        TextView modelText = view.findViewById(R.id.modelText);
        TextView registrationText = view.findViewById(R.id.registrationText);
        TextView fuelText = view.findViewById(R.id.fuelText);
        Cars car=carList.get(position);
        Cars idCar=getItem(position);

        view.setOnClickListener(v -> newActivity(idCar));


        modelText.setText("" + car.getModel());
        registrationText.setText("Fuel:" + car.getRegistrationNumber());
        fuelText.setText("Fuel: " + car.getFuelAmount());


        return view;
    }


    private void newActivity(Cars car) {
        Intent intent = new Intent(getContext(), ChoseCar.class);
        Bundle bundle=new Bundle();
        bundle.putString("idCar", car.getModel());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
