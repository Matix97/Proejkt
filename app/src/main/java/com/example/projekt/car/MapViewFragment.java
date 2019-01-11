package com.example.projekt.car;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MapViewFragment extends Fragment {

    private static final int REQ_PERMISSION = 0;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    MapView mMapView;
    private GoogleMap googleMap;
    private LocationManager locationManager;


    List<Cars> data = new ArrayList<>();
    List<Cars> finalData = new ArrayList<>();

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);
        //locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //  googleMap.setMyLocationEnabled(true);
                if (checkPermission())
                    googleMap.setMyLocationEnabled(true);
                else askPermission();

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(51.51, 19.24);
                //LatLng my=;
                // googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                downloadCars();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    // Asks for permission
    private void askPermission() {
        Log.d(TAG, "askPermission()");
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    if (checkPermission())
                        googleMap.setMyLocationEnabled(true);

                } else {
                    // Permission denied

                }
                break;
            }
        }
    }
    /*   @Override
   public void onLocationChanged(Location location) {

       latitude= location.getLatitude();
       longitude=location.getLongitude();

       LatLng loc = new LatLng(latitude, longitude);

       if (marker!=null){
           marker.remove();
       }

       marker=  map.addMarker(new MarkerOptions().position(loc).title("Sparx IT Solutions"));
       map.moveCamera(CameraUpdateFactory.newLatLng(loc));
       map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

   }*/
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
                    // CarArturAdapter arturAdapter = new CarArturAdapter(MyListActivity.this, R.layout.cars_adapter, finalData);
                    //setListAdapter(arturAdapter);
                    // For dropping a marker at a point on the Map
                    for(int i=0;i<finalData.size();i++) {
                        LatLng sydney = new LatLng(finalData.get(i).getLatitude(), finalData.get(i).getLongitude());

                        googleMap.addMarker(new MarkerOptions().position(sydney).title(finalData.get(i).getModel()).snippet(finalData.get(i).getRegistrationNumber()));
                    }
                } else
                    Toast.makeText(getContext(), "Error in GET cars ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE Error in GET cars ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
