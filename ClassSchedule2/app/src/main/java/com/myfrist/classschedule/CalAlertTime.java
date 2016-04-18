package com.myfrist.classschedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Locale;

/**
 * Created by Minnyclash on 24/3/2559.
 */
public class CalAlertTime extends AppCompatActivity implements Runnable {
    //String time_appointments = "2016-03-19 11:00:00";
    long time_travel = 60*60*1000;
    long time_notice= 10*60*1000;
    Date time_alerts;
    Date time_cal;
    MainActivity objmain;
    Button cal;
    String current = "18.8032379,98.9504395";
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
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
                new GeoTask(CalAlertTime.this).execute(url);

            }
        });

    }

    public CalAlertTime(String time_appointments) {


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
         time_cal = new Date((badlyDesignedOriginalDate.getTime() - time_travel) - time_notice);

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
    public void run(){


        while(true){
        try{
        Thread.sleep(10000);
      //  if (time_cal.getTime() <= time_alerts.getTime()) {
                           System.out.println("countdown:Now,I'm very happy " + time_alerts);
                       // } else
        System.out.println("countdown:done");

    } catch (InterruptedException e){}
}

}

}