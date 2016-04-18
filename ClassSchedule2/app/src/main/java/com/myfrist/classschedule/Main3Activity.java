package com.myfrist.classschedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

//import com.google.api.client.util.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Handler;


public class Main3Activity extends AppCompatActivity implements GeoTask.Geo {
    EditText edttxt_from,edttxt_to;
    Button btn_get;
    String str_from,str_to;
    TextView tv_result1,tv_result2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initialize();
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_from = edttxt_from.getText().toString();
                str_to = edttxt_to.getText().toString();
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + str_from + "&destinations=" + str_to + "&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8";
                new GeoTask(Main3Activity.this).execute(url);

            }
        });
//        Thread x = new Thread(new CalAlertTime());
//        x.start();
//        try {
//            calDate();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }
    @Override
    public void setDouble(String result) {
        String res[]=result.split(",");
        Double min=Double.parseDouble(res[0])/60;
        int dist=Integer.parseInt(res[1])/1000;
        tv_result1.setText("Duration= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");
        tv_result2.setText("Distance= " + dist + " kilometers");
        int hr = (int) (min / 60);
        int mins = (int)(min%60);
        Log.d("min2", String.valueOf(hr));
        Log.d("min2", String.valueOf(mins));
        Log.d("min2", String.valueOf(dist));

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    public void calDate() throws ParseException {



//            int time_cal; เวลาแจ้งเตือนที่คำนวณได้
//            int time_appointments; เวลานักหมาย
//            int time_travel; เวลาที่ใช้ในการเดินทาง
//            int time_notice; เวลาแจ้งเตือนล่วงหน้า
//            int current_time; เวลาปัจจบัน
//            int time_alerts; เวลาแจ้งเตือนจริงๆ
//            int five;
//        String time_appointments = "2016-03-19 11:00:00";
//        long time_travel = 60*60*1000;
//        long time_notice= 10*60*1000;
//       // String originalDateString = getDateTime(); // your current function
//       // System.out.println("originalDateString : " + time_appointments);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date badlyDesignedOriginalDate = dateFormat.parse(time_appointments);
//       // long diff = 60*60 * 1000; // milliseconds!
//        Date time_cal = new Date((badlyDesignedOriginalDate.getTime() - time_travel)-time_notice);
//
//      //  for (int i=0;i<=5;i++){
//        Date time_alerts= new Date();


//       while (time_cal.getTime() <= time_alerts.getTime()){
//
//           String current_time = getDateTime();
//           Date current_time2 = dateFormat.parse(current_time);
//           long five = 5*60*1000;
//          // Date time_alerts = new  Date(current_time2.getTime()+five);
//           System.out.println("time_alerts : " + time_alerts);
//
//           //  }
//           System.out.println("diffedDate : " + time_cal);
//
//       }

int o=0;
            while (o<0) {
//                System.out.println("Now,I'm very happy ");
//                Log.d("mint","mint I'm very happy");

            o++;
                CountDownTimer start = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        System.out.println("countdown: "+millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {
//                        if (time_cal.getTime() <= time_alerts.getTime()) {
//                            System.out.println("Now,I'm very happy " + time_alerts);
//                        } else
                      //  mTextFiled.setText("done");
                            System.out.println("countdown:done");// + time_cal);

                    }
                }.start();
                // Thread.sleep( 10* 1000);
            }




    }


    public void printDifference(Date startDate, Date endDate) throws ParseException {



    }


    private void initialize() {
        edttxt_from= (EditText) findViewById(R.id.editText_from);
        edttxt_to= (EditText) findViewById(R.id.editText_to);
        btn_get= (Button) findViewById(R.id.button_get);
        tv_result1= (TextView) findViewById(R.id.textView_result1);
        tv_result2=(TextView) findViewById(R.id.textView_result2);
    }
//
//        AutoCompleteTextView from = (AutoCompleteTextView) findViewById(R.id.from);
//        AutoCompleteTextView to = (AutoCompleteTextView) findViewById(R.id.to);
//
//        from.setText("Fisherman's Wharf, San Francisco, CA, United States");
//        to.setText("The Moscone Center, Howard Street, San Francisco, CA, United States");
//
//        from.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));
//        to.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));
//
////
//       final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

//        List<LatLng> latLngs = new ArrayList<LatLng>();


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


}
