package com.example.projekt.car.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.Exceptions.PersonDoesNotExist;
import com.example.projekt.car.MyListActivity;
import com.example.projekt.car.NfcActivity;
import com.example.projekt.car.R;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;
import com.example.projekt.car.data.PeopleDataBase;
import com.example.projekt.car.data.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HelloFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button nfc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.sample_face);

       /* TextView textView = view.findViewById(R.id.textView);
        PeopleDataBase peopleDataBase = new PeopleDataBase();
        Person person = null;
        try {
            person = peopleDataBase.getPerson((String) getArguments().get("dane"));
        } catch (PersonDoesNotExist personDoesNotExist) {
            personDoesNotExist.printStackTrace();
        }
        // textView.setText("Welcome "+person.getFirstName());*/
        this.view = view;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nfc = view.findViewById(R.id.nfcButton);
        nfc.setOnClickListener(this::onClick);

    }


    public static HelloFragment newInstance() {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override//todo Testing get cars-- working correctly
    public void onClick(View v) {
/*        CarService carService =ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call=carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),response.body().get(0).getModel(),Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(),"Error in GET cars ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(getActivity(),"FAILURE Error in GET cars ",Toast.LENGTH_SHORT).show();
            }
        });*/

        newActivity();
        //Toast.makeText(getActivity(),"NFC hear ",Toast.LENGTH_SHORT).show();
    }



    private void newActivity() {
        //  Intent intent = new Intent(getActivity(), MyListActivity.class);
        //  Bundle bundle=new Bundle();
        //  bundle.putString("idCar", "Audi");
        //  intent.putExtras(bundle);
        Intent intent = new Intent(getActivity(), NfcActivity.class);
        startActivity(intent);
    }
}

