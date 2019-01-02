package com.example.projekt.car;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RentedCarActivity extends AppCompatActivity {

    private TextView fuelAmount,faultDescription;
    private Button fuelButton,faultButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rented_car);
        fuelAmount=findViewById(R.id.fuelAmount);
        faultDescription=findViewById(R.id.faultDescritpion);
        fuelButton=findViewById(R.id.fuelButton);
        faultDescription=findViewById(R.id.faultButton);
        fuelButton.setOnClickListener((v)->{fueling(v);});
        faultButton.setOnClickListener(v->fault(v));
    }

    private void fueling(View v) {

    }
    private void fault(View v) {
    }
}
