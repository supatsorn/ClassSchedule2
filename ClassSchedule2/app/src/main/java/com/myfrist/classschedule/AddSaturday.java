package com.myfrist.classschedule;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSaturday extends AppCompatActivity {

    EditText sbj_name,sbj_num,detail;
    Button std_time,save,cancel;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Class_Schedule objClass_Schedul;
    private Location objLocation;
    static final int DILOG_ID = 0;
    int hour_x,minute_x;
    String settime,Sat= "Sat",location;
    private Spinner d_location,d_notice;
    private String[] strLocation,strNotice;
    private String strLocationChooed,strNoticeChooed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saturday);

        sbj_name = (EditText)findViewById(R.id.sbj_name);
        sbj_num = (EditText)findViewById(R.id.sbj_num);
        detail = (EditText)findViewById(R.id.detail);
        save = (Button)findViewById(R.id.save);

        showTimePickerDialog();
        showSpinner();

        //connect DB
        connectedSQLite();
        db = new MySQLiteOpenHelper(AddSaturday.this);
        data = db.getWritableDatabase();
        db.close();

        Addvalue();

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                strNoticeChooed = strNotice[0];
                // Another interface callback
            }
        });
    }

    private void Addvalue() {
        save.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        boolean isInserted = objClass_Schedul.addNewClass_Schedule(sbj_name.getText().toString(),
                                sbj_num.getText().toString(),Sat.toString(),settime.toString(),strLocationChooed.toString()
                                ,strNoticeChooed.toString(),detail.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(AddSaturday.this, "DATA Inserted ", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddSaturday.this, "DATA not Inserted ", Toast.LENGTH_LONG).show();


                        Toast.makeText(AddSaturday.this, "Click", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddSaturday.this, Saturday.class);
                        startActivity(intent);

                    }
                }
        );
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
            Toast.makeText(AddSaturday.this, formattedHour + ":" + formattedMinute, Toast.LENGTH_LONG).show();
        }
    };


}
