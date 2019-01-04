package com.example.projekt.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Fault;
import com.example.projekt.car.DTOs.Fuel;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentedCarActivity extends AppCompatActivity {

    private TextView fuelAmount, faultDescription, registrationText, modelText;
    private Button fuelButton, faultButton;
    private Switch isCritical;
    private CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
    private int carID;
    private boolean isCriticalAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rented_car);

        fuelAmount = findViewById(R.id.fuelAmount);
        faultDescription = findViewById(R.id.faultDescritpion);
        fuelButton = findViewById(R.id.fuelButton);
        faultButton = findViewById(R.id.faultButton);
        isCritical = findViewById(R.id.isCritical);
        registrationText = findViewById(R.id.registrationText);
        modelText = findViewById(R.id.modelText);

        isCritical.setOnCheckedChangeListener((buttonView, isChecked) -> isCriticalAnswer = isChecked);
        fuelButton.setOnClickListener(v -> fueling(v));
        faultButton.setOnClickListener(v -> fault(v));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        carID = bundle.getInt("carID");
        String reg = bundle.getString("registrationNumber");
        String mod = bundle.getString("model");
        // Toast.makeText(this, "ID: "+carID+"\nModel: "+mod+"\nRegistration: "+reg, Toast.LENGTH_LONG).show();
        registrationText.setText("" + reg);
        modelText.setText("" + mod);


    }

    private void fueling(View v) {
        try {
            Double fuel = Double.parseDouble(fuelAmount.getText().toString());
            //Toast.makeText(this, String.valueOf(fuel), Toast.LENGTH_SHORT).show();//todo remove-only tetsing
            Call<ResponseBody> call = carService.fuel(new Fuel(carID, fuel, new Date().getTime()));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RentedCarActivity.this, "good fueling", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RentedCarActivity.this, "error in sending fuel", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(RentedCarActivity.this, "something go wrong fuel", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show();
        }
    }

    private void fault(View v) {
        String descripiton = " ";

        descripiton = faultDescription.getText().toString();
        // descripiton= String.valueOf(isCriticalAnswer);
        //  Toast.makeText(this, descripiton, Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = carService.reportFault(new Fault(carID, isCriticalAnswer, descripiton, new Date().getTime()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RentedCarActivity.this, "good faulting", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RentedCarActivity.this, "error in sending fault", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RentedCarActivity.this, "something go wrong fault", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
