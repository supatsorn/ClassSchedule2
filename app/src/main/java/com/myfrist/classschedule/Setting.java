package com.myfrist.classschedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Setting extends AppCompatActivity {


    Button set_str_sem,set_str_sem2,set_end_sem,set_end_sem2,save,cancel;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Semester objSemester;
    private Context context;
    EditText semester_num,year,start_date,end_date;
    int year_x,month_x,day_x,hour_x,minute_x;
    int year_y,month_y,day_y,hour_y,minute_y;
    static final int DILOG_ID = 0;
 //   static final int DILOG2_ID = 1;
    static final int DILOG3_ID = 2;
//    static final int DILOG4_ID = 3;
    String date_time1,date_time2,date_str,time_str,date_en,time_en;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        semester_num = (EditText)findViewById(R.id.semester_num);
        year = (EditText)findViewById(R.id.year);
        save = (Button)findViewById(R.id.save);


        //รับค่าวันปัจจุบัน ใช้ในการแสดงวันที่
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        year_y = cal.get(Calendar.YEAR);
        month_y = cal.get(Calendar.MONTH);
        day_y = cal.get(Calendar.DAY_OF_MONTH);
        //แสดงปฎิฑิน
        showDatePickerDialog();
//        showTimePickerDialog();
        showDatePickerDialog2();
//        showTimePickerDialog2();

//
        Addvalue();

//
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.spinner, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        //connect DB
        connectedSQLite();
        db = new MySQLiteOpenHelper(Setting.this);
        data = db.getWritableDatabase();
        db.close();
         cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showDatePickerDialog() {
        set_str_sem = (Button)findViewById(R.id.set_str_sem);
        set_str_sem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );

    }
//    public  void showTimePickerDialog(){
//        set_str_sem2 = (Button)findViewById(R.id.set_str_sem2);
//        set_str_sem2.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showDialog(DILOG2_ID);
//
//                    }
//                }
//
//        );
//    }
    public void showDatePickerDialog2() {
        set_end_sem = (Button)findViewById(R.id.set_end_sem);
        set_end_sem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG3_ID);
                    }
                }
        );

    }
//    public  void showTimePickerDialog2(){
//        set_end_sem2 = (Button)findViewById(R.id.set_end_sem2);
//        set_end_sem2.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showDialog(DILOG4_ID);
//
//                    }
//                }
//
//        );
//    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DILOG_ID :
                return new DatePickerDialog(this, dpickerListener,year_x,month_x,day_x);
            case DILOG3_ID :
                return new DatePickerDialog(this, dpickerListener2,year_y,month_y,day_y);

        }
//        if (id == DILOG_ID)
//            return new DatePickerDialog(this, dpickerListner,year_x,month_x,day_x);

        return null;

//            return new TimePickerDialog(this, kTimePickerListner, hour_x, minute_x, false);

    }



    protected DatePickerDialog.OnDateSetListener dpickerListener
            =new  DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;

            String formattedDay = (String.valueOf(day_x));
            String formattedMonth = (String.valueOf(month_x));

            if(day_x < 10)
            {
                formattedDay = "0" + day_x;
            }

            if(month_x< 10)
            {
                formattedMonth = "0" + month_x;
            }
            date_str = year_x + "-" + formattedMonth + "-" + formattedDay;
            Toast.makeText(Setting.this,year_x+"-"+month_x+"-"+day_x,Toast.LENGTH_LONG).show();

        }
    };
//   protected TimePickerDialog.OnTimeSetListener kTimePickerListener
//            =new  TimePickerDialog.OnTimeSetListener(){
//
//
//        @Override
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            hour_x = hourOfDay;
//            minute_x = minute;
//            String formattedMinute = (String.valueOf(minute_x));
//            String formattedHour = (String.valueOf(hour_x));
//
//            if(minute_x < 10)
//            {
//                formattedMinute = "0" + minute_x;
//            }
//
//            if(hour_x< 10)
//            {
//                formattedHour = "0" + hour_x;
//            }
//            time_str = formattedHour+":"+formattedMinute+":00";
//            Toast.makeText(Setting.this,formattedHour+":"+formattedMinute,Toast.LENGTH_LONG).show();
//        }
//    };


    protected DatePickerDialog.OnDateSetListener dpickerListener2
            =new  DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_y = year;
            month_y = monthOfYear + 1;
            day_y = dayOfMonth;

            String formattedDay2 = (String.valueOf(day_y));
            String formattedMonth2 = (String.valueOf(month_y));

            if(day_y < 10)
            {
                formattedDay2 = "0" + day_y;
            }

            if(month_y< 10)
            {
                formattedMonth2 = "0" + month_y;
            }
            date_en = year_y + "-" + formattedMonth2 + "-" + formattedDay2;
            Toast.makeText(Setting.this,year_y+"-"+month_y+"-"+day_y,Toast.LENGTH_LONG).show();

        }
    };
//    protected TimePickerDialog.OnTimeSetListener kTimePickerListener2
//            =new  TimePickerDialog.OnTimeSetListener(){
//
//
//        @Override
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            hour_y = hourOfDay;
//            minute_y = minute;
//            String formattedMinute2 = (String.valueOf(minute_y));
//            String formattedHour2 = (String.valueOf(hour_y));
//
//            if(minute_y < 10)
//            {
//                formattedMinute2 = "0" + minute_y;
//            }
//
//            if(hour_y< 10)
//            {
//                formattedHour2 = "0" + hour_y;
//            }
//            time_en = formattedHour2+":"+formattedMinute2+":00";
//            Toast.makeText(Setting.this,formattedHour2+":"+formattedMinute2,Toast.LENGTH_LONG).show();
//        }
//    };
//    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
//        int day = datePicker.getDayOfMonth();
//        int month = datePicker.getMonth();
//        int year =  datePicker.getYear();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);
//
//        return calendar.getTime();
//    }
    //add value
    public void Addvalue(){
        save.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        date_time1 = date_str+" "+"00:00:00";
                        date_time2 = date_en+" "+"00:00:00";
                        Log.i("mint",date_time1+"AND"+date_time2);
                        boolean isInserted = objSemester.addNewSemester(semester_num.getText().toString(),
                                year.getText().toString(), date_time1.toString(),date_time2.toString());
                        if (isInserted = true) {
                            Intent intent = new Intent(Setting.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(Setting.this, "DATA Inserted ", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(Setting.this, "DATA not Inserted ", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
    private void connectedSQLite() {
        //connectSQLite
        objSemester = new Semester(this);

//        objClass_Schedule = new Class_Schedule(this);
//        objLocation = new Location(this);
    }


}
