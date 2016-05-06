package com.myfrist.classschedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddWednesday extends AppCompatActivity {

    EditText sbj_name,sbj_num,detail;
    Button std_time,save,cancel;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Class_Schedule objClass_Schedul;
    private Location objLocation;
    static final int DILOG_ID = 0;
    int hour_x,minute_x;
    int local_l;
    String settime,Wed= "Wed",location;
    private Spinner d_location,d_notice;
    private String[] strLocation,strNotice;
    private String strLocationChooed,strNoticeChooed;
    public static final String inputFormat = "yyyy-MM-dd HH:mm:ss";
    private Date date;
    public String current_time;
    public String date2;
    private Date dateCompareOne;
    private Date dateCompareTwo;
    private String start_date,days;
    private String end_date,end_date_x;
    private int wCount_i=1;
    public ArrayList<String> loca_wed;
    ArrayList<String> myList_sem;
    public GPSTracker gpsTracker;
    String lat_lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wednesday);

        gpsTracker = new GPSTracker(this);
        if (gpsTracker.getIsGPSTrackingEnabled()) {

            Double Lat = gpsTracker.latitude;
            Double Lng = gpsTracker.longitude;
            Log.i("Lat-Lng_Hooooooooooo___Lat", String.valueOf(Lat));
            Log.i("Lat-Lng_Hooooooooooo___Lng", String.valueOf(Lng));
        }else {
            Log.i("Lat-Lng_Hooooooooooo", "Can not show lat long");
        }

        sbj_name = (EditText)findViewById(R.id.sbj_name);
        sbj_num = (EditText)findViewById(R.id.sbj_num);
        detail = (EditText)findViewById(R.id.detail);
        save = (Button)findViewById(R.id.save);

        showTimePickerDialog();
        showSpinner();
        

        //connect DB
        connectedSQLite();
        db = new MySQLiteOpenHelper(AddWednesday.this);
        data = db.getWritableDatabase();
        db.close();

        Addvalue();

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(AddWednesday.this, Wednesday.class);
//                startActivity(intent);
            }
        });
        //spinner Notice-------------------
        //Location
        strLocation = getResources().getStringArray(R.array.data_location);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strLocation);
// Specify the layout to use when the list of choices appears
        d_location.setAdapter(objAdapter);
// Apply the adapter to the spinner
        d_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                strLocationChooed = strLocation[i];
                Log.i("DATA", strLocationChooed);
                if(l==0){
                    local_l = 1;
                    lat_lng = "18.8015272,98.9511638";
                }else if (l==1){
                    local_l = 2;
                    lat_lng ="18.8016565,98.9547273";
                }else if(l==2){
                    local_l=3;
                    lat_lng ="18.8032285,98.952047";
                }else if (l==3){
                    local_l=4;
                    lat_lng ="18.803145,98.953478";
                }else if (l==4){
                    local_l=5;
                    lat_lng="18.8031471,98.9525578";
                }else if (l==5){
                    local_l=6;
                    lat_lng="18.8034241,98.9521138";
                }else if (l==6){
                    local_l=7;
                    lat_lng="18.8011958,98.9532585";
                }else if (l==7){
                    local_l=8;
                    lat_lng="18.8029354,98.9547791";
                }else if (l==8){
                    local_l=9;
                    lat_lng="18.8020137,98.9517463";
                }else if (l==9){
                    local_l=10;
                    lat_lng="18.8025151,98.9508895";
                }else if (l==10){
                    local_l=11;
                    lat_lng="18.8032379,98.9504395";
                }else if (l==11){
                    local_l=12;
                    lat_lng="18.8032379,98.9504395";
                }else
                    local_l=0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                strLocationChooed = strLocation[0];
                // Another interface callback
            }
        });

        //Notice
        strNotice = getResources().getStringArray(R.array.data_notice);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> objAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strNotice);
// Specify the layout to use when the list of choices appears
        d_notice.setAdapter(objAdapter2);
// Apply the adapter to the spinner
        d_notice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int m, long l) {
                strNoticeChooed = strNotice[m];
                Log.i("DATA", strNoticeChooed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                strNoticeChooed = strNotice[0];
                // Another interface callback
            }
        });
      
        //Compare Date
        myList_sem= getAllsemList();

    }

    private void Addvalue() {
        save.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String Sbj_name = sbj_name.getText().toString();
                        String Sbj_num = sbj_num.getText().toString();
                        String Detail = detail.getText().toString();
                        if (Sbj_name.matches("") || Sbj_num.matches("")) {
                            openDialog();
                        } else {
                            boolean isInserted = objClass_Schedul.addNewClass_Schedule(sbj_name.getText().toString(),
                                    sbj_num.getText().toString(), Wed.toString(), settime.toString(), local_l
                                    , strNoticeChooed.toString(), detail.getText().toString());
                            if (isInserted = true) {
                                try {
                                    compareDates();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(AddWednesday.this, "DATA Inserted ", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(AddWednesday
                                        .this, "DATA not Inserted ", Toast.LENGTH_LONG).show();


//                        Toast.makeText(AddWednesday.this, "Click", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddWednesday.this, Wednesday.class);
                            startActivity(intent);

                        }
                    }


                }
        );
    }
    //Alert dialog setting
    public void openDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Warning!,Please complete data ");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(AddWednesday.this, AddWednesday.class);
                startActivity(intent);
                // Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void connectedSQLite() {
        //connectSQLite
        objClass_Schedul = new Class_Schedule(this);
        objLocation = new Location(this);

    }

    private void showSpinner() {
        d_location = (Spinner)findViewById(R.id.location);
        d_notice = (Spinner)findViewById(R.id.notice);
    }

    private void showTimePickerDialog() {
        std_time = (Button)findViewById(R.id.std_time);
        std_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );

    }
    protected Dialog onCreateDialog(int id){

        if (id == DILOG_ID)
            return new TimePickerDialog(this, kTimePickerListener, hour_x, minute_x, false);

        return null;
    }
    protected TimePickerDialog.OnTimeSetListener kTimePickerListener
            =new  TimePickerDialog.OnTimeSetListener(){


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            String formattedMinute = (String.valueOf(minute_x));
            String formattedHour = (String.valueOf(hour_x));

            if(minute_x < 10)
            {
                formattedMinute = "0" + minute_x;
            }

            if(hour_x< 10)
            {
                formattedHour = "0" + hour_x;
            }
            settime = formattedHour+":"+formattedMinute+":00";
            Toast.makeText(AddWednesday.this, formattedHour + ":" + formattedMinute, Toast.LENGTH_LONG).show();
        }
    };


    private void compareDates() throws ParseException {
        current_time = getDateTime();
        Log.i("current_time",current_time);
        date = parseDate(current_time);
        Log.i("date", String.valueOf(date));
        date2 = getDateTime2();
        Log.i("date2", date2);
        String today = convertDateTimeFormat();
        Log.i("current_time today", today);
        dateCompareOne = parseDate(start_date);
        dateCompareTwo = parseDate(end_date);
        Log.i("cent_time start", String.valueOf(dateCompareOne));
        Log.i("current_time end", String.valueOf(dateCompareTwo));
        if ( dateCompareOne.before( date ) && dateCompareTwo.after(date)) {
            if ((today.equals("Wed"))||(today.equals("พ."))) {
                for (int i = 0; i < wCount_i; i++) {
                    String time_appointments = date2 + " " + settime;
                    String lat_long = lat_lng;
                    String time_notice = strNoticeChooed;
                    Log.i("time_notice ", time_notice);
                    Thread x = new Thread(new CalAlertTime(getApplicationContext(), time_appointments, lat_long, time_notice, gpsTracker));
                    x.start();
                }
            }
            Log.i("Today ", "Today is not Wednesday");
        }
    }
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        Log.i("Day", String.valueOf(date));

        return dateFormat.format(date);
    }
    private String getDateTime2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }
    private String convertDateTimeFormat() {

        DateFormat dateFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        Date date = new Date();
        Log.i("current_time DayMint2", String.valueOf(date));
        return dateFormat.format(date);
    }
    public ArrayList<String> getAllsemList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Semester ;", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            start_date = cursor.getString(3);
            end_date =(cursor.getString(4));
            Log.i(" mint start,end", start_date);
            Log.i("mint start,end", end_date);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }


}
