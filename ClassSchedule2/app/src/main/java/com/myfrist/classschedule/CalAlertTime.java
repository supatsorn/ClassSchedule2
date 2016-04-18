package com.myfrist.classschedule;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Locale;

/**
 * Created by Minnyclash on 24/3/2559.
 */
public class CalAlertTime extends AppCompatActivity implements Runnable {
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    //String time_appointments = "2016-03-19 11:00:00";
    long time_travel = 60*60*1000;
    long time_notice= 10*60*1000;
    Date time_alerts;
    Date time_cal;
    MainActivity objmain;
    Button cal;
    String current = "18.803632,98.9523349";
    String Distance;
//    Double Duration;
    private GeoTask mmm;
    ProgressDialog pd;
    Context mContext;
    Double duration;
//    Geo geo1;
    public Double min;
    public CalAlertTime objCAlerTime;
    TextView tv_result1,tv_result2;

    public ArrayList<String> start_time_mon,start_time_tue,start_time_wed,start_time_thu,start_time_fri,start_time_sat,start_time_sun;
    private int mCount,tuCount,wCount,thCount,fCount,saCount,suCount;
    private int mCount_i,tuCount_i,wCount_i,thCount_i,fCount_i,saCount_i,suCount_i;
//    private Main4Activity objMain4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       String  latlngs = objMain4.latLngs;
//        Log.d("min3", latlngs);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // str_from=edttxt_from.getText().toString();
                //str_to=edttxt_to.getText().toString();
//                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//                new GeoTask(CalAlertTime.this).execute(url);

            }
        });



    }

    public CalAlertTime(String time_appointments,String lat_long,String time_notice) {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + lat_long + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
        Log.d("url", url);
        new GeoTask(CalAlertTime.this).execute(url);
        Log.d("time_appointments", time_appointments);
         String originalDateString = getDateTime(); // your current function
          System.out.println("countdown created");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date badlyDesignedOriginalDate = null;
        try {
            badlyDesignedOriginalDate = dateFormat.parse(originalDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // long diff = 60*60 * 1000; // milliseconds!
        // time_cal = new Date((badlyDesignedOriginalDate.getTime() - time_travel) - time_notice);

        //  for (int i=0;i<=5;i++){
         time_alerts = new Date();
    }

//    public CalAlertTime(String time_appointments, String current_time) {
//        time_appointments = objmain.time_appointments;
//        Log.d("time_appointments", time_appointments);
//    }



    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        Log.i("Day", String.valueOf(date));

        return dateFormat.format(date);
    }



    public void run() {


        while (true) {
            try {
                Thread.sleep(10000);
                //  if (time_cal.getTime() <= time_alerts.getTime()) {
                System.out.println("countdown:Now,I'm very happy " + time_alerts);
                // } else
                System.out.println("countdown:done");

            } catch (InterruptedException e) {
            }
        }

    }

}