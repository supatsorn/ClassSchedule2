package com.myfrist.classschedule;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.Date;

import java.util.Locale;
import java.util.logging.Handler;

/**
 * Created by Minnyclash on 24/3/2559.
 */
public class CalAlertTime extends Activity implements Runnable, GeoTask.AsyncResponse {
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    //String time_appointments = "2016-03-19 11:00:00";
    String time_travel="5.0";
    Date time_alerts;
    Date time_cal;
    MainActivity objmain;
    Button cal;
    String current = "18.803632,98.9523349";
    String current2="18.8011958,98.9532585";
    String Distance;
    public Alarm objAlarm;
    String Output;
    Date time_appointments,currentDate;
    String time_notice;
    String lat_lng;
    Handler mHandler;
    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    private ListView listAlarm;
    public static ArrayList<String> listValue;
    TimePickerDialog timePickerDialog;
    private AlarmManager mAlarmManager;
    private PendingIntent alarmIntent;
    private GPSTracker gps;
    final static int RQS_1 = 1;

    Context mContext;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
//        cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // str_from=edttxt_from.getText().toString();
//                //str_to=edttxt_to.getText().toString();
//                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current2 + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//                new GeoTask(CalAlertTime.this).execute(url);
//
//            }
//        });
//
//
//
//    }
//@Override
////public void onCreate(Bundle savedInstanceState) {
////    super.onCreate(savedInstanceState);
////    setContentView(R.layout.test_alam);
////    Calendar nnnn = Calendar.getInstance();
////    nnnn.getTime();
////    setAlarm(nnnn);
//
////                    final int _id = (int) System.currentTimeMillis();
////
////                    Intent intent = new Intent(getBaseContext(), ShowEvent.class);
////                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
////                    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
////                    alarmManager.set(AlarmManager.RTC_WAKEUP, nnnn.getTimeInMillis(), pendingIntent);
//
//}
    public CalAlertTime(Context mContext,String time_appointments,String lat_long,String time_notice,GPSTracker gpsTracker) throws ParseException {

        this.mContext = mContext;
        this.lat_lng=lat_long;
//        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current2 + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//        Log.d("URL", url);
        this.time_notice = time_notice;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time_appointments2 = dateFormat.parse(time_appointments);
        this.time_appointments=time_appointments2;
        this.gps = gpsTracker;
//
//        Double Lat = gps.getLatitude();
//        Double Lng = gps.getLongitude();
//        Log.i("Latitude,","Longitude"+gps.getLatitude()+gps.getLongitude());
//        if(Lat==0.0) {
//            Log.e("current_lat_lng","Have not current lat and long");
//            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current2 + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//            Log.d("URL_Current", url);
//            GeoTask geoTask = new GeoTask(CalAlertTime.this);
//            geoTask.delegate = this;
//            geoTask.execute(url);
//        }else {
//            String Lat2 = String.valueOf(Lat);
//            String Lng2 = String.valueOf(Lng);
//            String current_lat_lng = Lat2+","+Lng2;
//            Log.e("current_lat_lng And location lat_lng",current_lat_lng+" And "+lat_lng);
//            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current_lat_lng + "&destinations=" + lat_lng + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//            Log.d("URL", url);
//            GeoTask geoTask = new GeoTask(CalAlertTime.this);
//            geoTask.delegate = this;
//            geoTask.execute(url);
//        }


        Log.d("This is class","CalAlertTime");
//        Log.d("min time", String.valueOf(result));

        // long diff = 60*60 * 1000; // milliseconds!
        //time_cal = new Date((badlyDesignedOriginalDate.getTime() - time_travel) - time_notice);

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

    public void processFinish(String output){
        this.time_travel=output;
        Log.d("this.time_travel", "" + time_travel);

        //Toast.makeText(CalAlertTime.this,"MIN = "+output,Toast.LENGTH_LONG);
    }

    private PendingIntent pendingIntent;


    public void run() {



//        gps.getLatitude();
//        gps.getLongitude();
//        Log.i("Latitude,","Longitude"+gps.getLatitude()+gps.getLongitude());
//
//        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current2 + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
//        Log.d("URL", url);
//
//
//        GeoTask geoTask = new GeoTask(CalAlertTime.this);
//        geoTask.delegate = this;
//        geoTask.execute(url);
       while (true) {
            try {

                Thread.sleep(10000);
                Double Lat = gps.getLatitude();
                Double Lng = gps.getLongitude();
                Log.i("Latitude,", "Longitude" + gps.getLatitude() + gps.getLongitude());
                if(Lat==0.0) {
                    Log.e("current_lat_lng","Have not current lat and long");
                    String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current + "&destinations=" + current2 + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
                    Log.d("URL_Current", url);
                    GeoTask geoTask = new GeoTask(CalAlertTime.this);
                    geoTask.delegate = this;
                    geoTask.execute(url);
                }else {
                    String Lat2 = String.valueOf(Lat);
                    String Lng2 = String.valueOf(Lng);
                    String current_lat_lng = Lat2+","+Lng2;
                    Log.e("current_lat_lng And location lat_lng",current_lat_lng+" And "+lat_lng);
                    String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + current_lat_lng + "&destinations=" + lat_lng + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
                    Log.d("URL", url);
                    GeoTask geoTask = new GeoTask(CalAlertTime.this);
                    geoTask.delegate = this;
                    geoTask.execute(url);
                }

                Date time_cal = calAlert(time_appointments, time_notice, time_travel);
                String currentDate1 = getDateTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentDate = dateFormat.parse(currentDate1);
                this.currentDate=currentDate;
                Log.i("WoW_time_cal","Caltime = "+ String.valueOf(time_cal)+" current = "+String.valueOf(currentDate));
                if (time_cal.getTime() <= currentDate.getTime()) {

//                    Calendar nnnn = Calendar.getInstance();
//
//                    nnnn.getTime();
                    Calendar nnnn = Calendar.getInstance();
                    nnnn.getTime();
                    final int _id = (int) System.currentTimeMillis();

                    Intent intent = new Intent(mContext, AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, _id, intent, 0);
                    AlarmManager alarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, nnnn.getTimeInMillis(), pendingIntent);

//                    setAlarm(nnnn);
//                    Intent i = new Intent();
//                    i.setClass(this, AlarmReceiver.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                    Intent intent = new Intent(this, AlarmReceiver.class);
//                    startActivity(intent);
//                    mintMinny();
//                    Intent intent = new Intent(this, ShowEvent.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this,  0, intent, 0);
//                    mAlarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//
//                    // Cancel any existing service(s)
//                    mAlarmManager.cancel(pendingIntent);
//                    Intent alarmIntent = new Intent(CalAlertTime.this, AlarmReceiver.class);
//                    pendingIntent = PendingIntent.getBroadcast(CalAlertTime.this, 0, alarmIntent, 0);
//                    startActivity(new Intent(CalAlertTime.this, Alarm.class));
                 //getAlarm();
//                    Calendar nnnn = Calendar.getInstance();
//                    nnnn.getTime();
//                    final int _id = (int) System.currentTimeMillis();
//
//                    Intent intent = new Intent(getBaseContext(), ShowEvent.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
//                    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, nnnn.getTimeInMillis(), pendingIntent);



                System.out.println("countdown:Now,I'm very happy ");
                break;

                }else {
                    System.out.println("countdown:Nottttttttttttttttttttttttttttt,I'm very happy ");
                }
////                //  if (time_cal.getTime() <= time_alerts.getTime()) {
////                System.out.println("countdown:Now,I'm very happy " + time_alerts);
////                // } else
////                System.out.println("countdown:done");
////
            } catch (InterruptedException e) {
            } catch (ParseException e) {
                e.printStackTrace();
            }
       }

    }

   public void mintMinny(){
//       Intent intent = new Intent(CalAlertTime.this, Alarm.class);
//       startActivity(intent);
//       Calendar nnnn = Calendar.getInstance();
//       nnnn.getTime();
//       final int _id = (int) System.currentTimeMillis();
//
//       Intent intent = new Intent(this, AlarmReceiver.class);
//       PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, intent, PendingIntent.FLAG_ONE_SHOT);
//       AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//       alarmManager.set(AlarmManager.RTC_WAKEUP, nnnn.getTimeInMillis(), pendingIntent);


   }
    public Date calAlert(Date time_appointments_, String time_notice_, String time_travel_) {
        int time_notice1 = Integer.parseInt(time_notice_);
        int time_notice2 = time_notice1 * 60 * 1000;
        Log.i("Time_travel.......................",time_travel_);
        double time_travel1 = Double.parseDouble(time_travel_);
        double time_travel2 = time_travel1 * 60 * 1000;
        Log.i("WoW_time_travel2", String.valueOf(time_travel1));
//
        Log.i("WoW_time_appointments2 ", String.valueOf(time_appointments_));
//        //  Log.i("WoW_time_travel2 ", String.valueOf(time_travel2));
////        Date time_notice2 = dateFormat.parse(currentDateTimeNotice);
        Log.i("WoW_time_notice2 ", String.valueOf(time_notice2));
////        Date time_travel2 = dateFormat.parse(currentDateTimeTravel);
        Date time_cal = new Date((long) ((time_appointments_.getTime() - time_travel2)) - time_notice2);

//        Log.i("WoW_time_cal", String.valueOf(time_cal));
////        int time_notice2 = Integer.parseInt(time_notice);
//
//
//        while (true) {
//            try {
//                Thread.sleep(10000);
//
//
////                if (time_cal.getTime() <= currentDate.getTime()) {
//
////                    Intent intent = new Intent(mContext,Alarm.class);
////                    mContext.startActivity(intent);
////                    ((Activity)mContext).finish();
//
////                    objAlarm.getAlarm();
////                    Calendar nnnn = Calendar.getInstance();
////
////                    nnnn.getTime();
////                    Alarm alarm = new Alarm();
////                    Log.i("Alarmmmmmmmmmmmmmmmmmm", String.valueOf(alarm));
////                    alarm.setAlarm(nnnn);
//                System.out.println("countdown:Now,I'm very happy ");
////                    mainActivity.x.interrupt();;
//                break;
////
////                }else {
////                    System.out.println("countdown:Nottttttttttttttttttttttttttttt,I'm very happy ");
////                }
////                  if (time_cal.getTime() !=0 ){
////                System.out.println("countdown:Now,I'm very happy " );
////                 } else
////                System.out.println("countdown:done");
//
//            } catch (InterruptedException e) {
//            }
//        }
//
        return time_cal;
    }
//    public Alarm getAlarm(){
//
//
//
//        Calendar nnnn = Calendar.getInstance();
//        nnnn.getTime();
//        Log.i("Calendar nnnn", String.valueOf(nnnn));
//        setAlarm(nnnn);
//        return null;
//    }
//    public void setAlarm(Calendar targetCal) {
//        Log.i("targetCal.getTime() = ", targetCal.getTime().toString());
////        listValue.add(targetCal.getTime() + "");
//
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
////        listAlarm.setAdapter(adapter);
////        Log.i("listValue", String.valueOf(listValue));
//        final int _id = (int) System.currentTimeMillis();
//
//        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValue);
////        listAlarm.setAdapter(adapter);
//    }
    }