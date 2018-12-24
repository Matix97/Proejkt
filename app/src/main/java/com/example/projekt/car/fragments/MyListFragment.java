package com.example.projekt.car.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projekt.car.CarListAdapter;

import com.example.projekt.car.R;
import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;


import java.util.ArrayList;


public class MyListFragment extends android.support.v4.app.ListFragment  {

    private ArrayList<Car> carArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        carArrayList = new CarsDataBase().getCarArrayList();
        CarListAdapter adapter1 = new CarListAdapter(getActivity(), R.layout.car_item_list_adapter, carArrayList);
        setListAdapter(adapter1);
    }

    public static MyListFragment newInstance() {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



}
