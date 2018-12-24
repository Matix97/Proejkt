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
import com.example.projekt.car.ChoseCar;
import com.example.projekt.car.Exceptions.PersonDoesNotExist;
import com.example.projekt.car.R;
import com.example.projekt.car.data.PeopleDataBase;
import com.example.projekt.car.data.Person;


public class HelloFragment extends Fragment implements View.OnClickListener {
    private View view;
    private  Button nfc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment, container, false);

        ImageView imageView=view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.sample_face);

        TextView textView=view.findViewById(R.id.textView);
        PeopleDataBase peopleDataBase=new PeopleDataBase();
        Person person=null;
        try {
           person= peopleDataBase.getPerson((String)getArguments().get("dane"));
        } catch (PersonDoesNotExist personDoesNotExist) {
            personDoesNotExist.printStackTrace();
        }
        textView.setText("Welcome "+person.getFirstName());
        this.view=view;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nfc= view.findViewById(R.id.nfcButton);
        nfc.setOnClickListener(this::onClick);

    }


    public static HelloFragment newInstance() {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
       newActivity();
    }
    private void newActivity() {
        Intent intent = new Intent(getActivity(), ChoseCar.class);
        Bundle bundle=new Bundle();
        bundle.putString("idCar", "Audi");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

