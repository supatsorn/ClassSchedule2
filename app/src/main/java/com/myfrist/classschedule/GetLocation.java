package com.myfrist.classschedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import android.util.Log;

import java.util.ArrayList;

public class GetLocation extends  Activity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    public ArrayList<String> lat_lng;
    String lat;
    String provider;
    protected double latitude;
    protected double longitude;
    protected boolean gps_enabled,network_enabled;
   
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location; // location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        txtLat = (TextView) findViewById(R.id.textview1);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        Log.d("strList", String.valueOf(lat_lng));
    }

    @Override
    public void onLocationChanged(Location location) {
//        ArrayList<String> locationList = new ArrayList<String>();
        txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
//        String strList = location.getLatitude()+","+location.getLongitude();
//        lat_lng.add(strList);
//        Log.d("strList", String.valueOf(lat_lng));


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","enable");
    }
}
