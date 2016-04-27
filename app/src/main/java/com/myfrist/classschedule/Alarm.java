package com.myfrist.classschedule;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Alarm extends Activity {

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    private ListView listAlarm;
    public static ArrayList<String> listValue;
    Context mContext;
    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GeoTask geoTask;

    /**
     * Called when the activity is first created.
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_alam);

        listAlarm = (ListView) findViewById(R.id.listView1);
        listValue = new ArrayList<>();
//        getAlarm();
        Calendar nnnn = Calendar.getInstance();

        nnnn.getTime();
//        Log.i("uuuuuuuueeeeeeeeuuuuuuuuuuu", mmmm);

        setAlarm(nnnn);
    }
    public Alarm getAlarm(){

        onCreate(null);

        Calendar nnnn = Calendar.getInstance();
        nnnn.getTime();
        Log.i("Calendar nnnn", String.valueOf(nnnn));
        setAlarm(nnnn);
//uuuuuuuueeeeeeeeuuuuuuuuuuu: java.util.GregorianCalendar[time=1461378839459,areFieldsSet=true,lenient=true,zone=Asia/Bangkok,firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2016,MONTH=3,WEEK_OF_YEAR=17,WEEK_OF_MONTH=4,DAY_OF_MONTH=23,DAY_OF_YEAR=114,DAY_OF_WEEK=7,DAY_OF_WEEK_IN_MONTH=4,AM_PM=0,HOUR=9,HOUR_OF_DAY=9,MINUTE=33,SECOND=59,MILLISECOND=459,ZONE_OFFSET=25200000,DST_OFFSET=0]
        return null;
    }
    public void setAlarm(Calendar targetCal) {
        Log.i("targetCal.getTime() = ", targetCal.getTime().toString());
        listValue.add(targetCal.getTime() + "");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
        listAlarm.setAdapter(adapter);
        Log.i("listValue", String.valueOf(listValue));
        final int _id = (int) System.currentTimeMillis();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValue);
        listAlarm.setAdapter(adapter);
    }
}