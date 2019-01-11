/*
package com.example.projekt.car;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import java.util.Date;

import static android.content.Context.LOCATION_SERVICE;

public class MyLocalization  {

    private double longitiude;
    private double latitiude;
 MyLocalization() {
     Criteria kr = new Criteria();
     LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
     String theBestSupplier = locationManager.getBestProvider(kr, true);

     if (ActivityCompat.checkSelfPermission(getContext(), Manifest.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         // TODO: Consider calling
         //    ActivityCompat#requestPermissions
         // here to request the missing permissions, and then overriding
         //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
         //                                          int[] grantResults)
         // to handle the case where the user grants the permission. See the documentation
         // for ActivityCompat#requestPermissions for more details.
         return;
     }
     Location location = locationManager.getLastKnownLocation(theBestSupplier);
     longitiude = location.getLongitude();
     latitiude = location.getLatitude();
 }
}
*/
