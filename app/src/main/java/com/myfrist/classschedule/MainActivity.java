package com.myfrist.classschedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    ListView mintListView;
    Button monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    Button btn_gettime;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Semester m;
    private Semester c;
    private Semester objSemester;
    private Class_Schedule objClass_Schedule;
    private Location objLocation;
    private CalAlertTime objCAlAlertime;
    private GetLocation objgetlocation;
    TextView tv_result1,tv_result2;
    //--------
    public static final String inputFormat = "yyyy-MM-dd HH:mm:ss";
    private Date date;
    public String current_time;
    public String date2;
    private Date dateCompareOne;
    private Date dateCompareTwo;
    private String start_date,days;
    private String end_date,end_date_x;
    private int mCount=0,tuCount=0,wCount=0,thCount=0,fCount=0,saCount=0,suCount=0;
    private int mCount_i,tuCount_i,wCount_i,thCount_i,fCount_i,saCount_i,suCount_i;
    public ArrayList<String> start_time_mon,start_time_tue,start_time_wed,start_time_thu,start_time_fri,start_time_sat,start_time_sun;
    public ArrayList<String> loca_mon,loca_tue,loca_wed,loca_thu,loca_fri,loca_sat,loca_sun;
    public ArrayList<String> noti_mon,noti_tue,noti_wed,noti_thu,noti_fri,noti_sat,noti_sun;
    ArrayList<String> myList_sem;
    ArrayList<String> myList_class;
    public String time_appointments;
    private String strGetLocation;
    public String lat,lng,lat_lng;
    public Thread x;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //monday
        monday = (Button) findViewById(R.id.mon);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Monday.class);
                startActivity(intent);
            }
        });

        tuesday = (Button) findViewById(R.id.tue);
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Tuesday.class);
                startActivity(intent);
            }
        });
        wednesday = (Button) findViewById(R.id.wed);
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Wednesday.class);
                startActivity(intent);
            }
        });
        wednesday = (Button) findViewById(R.id.wed);
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Wednesday.class);
                startActivity(intent);
            }
        });
        thursday = (Button) findViewById(R.id.thu);
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Thursday.class);
                startActivity(intent);
            }
        });
        friday = (Button) findViewById(R.id.fri);
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Friday.class);
                startActivity(intent);
            }
        });
        saturday = (Button) findViewById(R.id.sat);
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Saturday.class);
                startActivity(intent);
            }
        });
        sunday = (Button) findViewById(R.id.sun);
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Sunday.class);
                startActivity(intent);
            }
        });
        Button cal = (Button) findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Alarm.class);
                intent.putExtra("name", "this is Main2");
                startActivity(intent);
            }
        });



        //connected SQLite
        connectedSQLite();
        db = new MySQLiteOpenHelper(MainActivity.this);
        data = db.getWritableDatabase();
        db.close();
        myList_sem= getAllsemList();
        myList_class= getAllclassList();
        start_time_mon = getAllMonList();
        Log.i("start_time_mon", String.valueOf(start_time_mon));
        start_time_tue = getAllTueList();
        start_time_wed = getAllWList();
        start_time_thu = getAllThuList();
        start_time_fri = getAllFriList();
        start_time_sat = getAllSatList();
        start_time_sun = getAllSunList();
        Log.i("myList_class", String.valueOf(myList_class));
        Log.i("start_time_sunkk", String.valueOf(start_time_sun));
        //get lat long
        loca_mon = getAllLocalMonList();
        loca_tue = getAllLocalTueList();
        loca_wed = getAllLocalWList();
        loca_thu = getAllLocalThuList();
        loca_fri = getAllLocalFriList();
        loca_sat = getAllLocalSatList();
        loca_sun = getAllLocalSunList();

        //get notice
        noti_mon = getAllNotiMonList();
        noti_tue = getAllNotiTueList();
        noti_wed = getAllNotiWList();
        noti_thu = getAllNotiThuList();
        noti_fri = getAllNotiFriList();
        noti_sat = getAllNotiSatList();
        noti_sun = getAllNotiSunList();

        try {
            compareDates();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);

    //convert date format
    private String convertDateTimeFormat() {

        DateFormat dateFormat = new SimpleDateFormat("EEE",Locale.getDefault());
        Date date = new Date();
//dateFormat.setTimeZone(); optionally specify timezone
 //       String day = dateFormat.format(date);
        Log.i("current_time DayMint2", String.valueOf(date));
        return dateFormat.format(date);
    }

    //get current time
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

//        Log.i("Day", String.valueOf(date));

        return dateFormat.format(date);
    }
//    String getDateTime() {
//        //String calen = calendar.toString();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
//
//
//        return dateFormat.format(date);
//    }

    private void compareDates() throws ParseException {
//        Calendar now = Calendar.getInstance();
//
//        int hour = now.get(Calendar.HOUR);
//        int minute = now.get(Calendar.MINUTE);
//
//        date = parseDate(hour + ":" + minute);

        current_time = getDateTime();
        Log.i("current_time",current_time);
        date = parseDate(current_time);
        Log.i("date", String.valueOf(date));
        date2 = getDateTime2();
        Log.i("date2", date2);
//        Log.i("current_time", current_time);
        String today = convertDateTimeFormat();
        Log.i("current_time today", today);
       String start_date2="2016-04-09 08:00:00";
        String end_date2="2016-05-12 08:00:00";
        dateCompareOne = parseDate(start_date2);
        dateCompareTwo = parseDate(end_date2);
        Log.i("cent_time start", String.valueOf(dateCompareOne));
        Log.i("current_time end", String.valueOf(dateCompareTwo));
        ArrayList<String> days= new ArrayList<>();
                        days.add("Mon");

        if ( dateCompareOne.before( date ) && dateCompareTwo.after(date)) {
            //yada yada
//            String today = convertDateTimeFormat();
//            Log.i("current_time today", today);
                if((today.equals("Mon"))||(today.equals("จ."))) {
                    for(int i=0;i<mCount_i;i++){

                        String time_appointments = date2 + " " + start_time_mon.get(i);
                        String lat_long = loca_mon.get(i);
                        String time_notice = noti_mon.get(i);
                        Log.i("lat_long", lat_long);
                        Log.i("time_appointments", String.valueOf(time_appointments));
                        Thread x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();

                    }
                    Log.i("Pancake ", "Monday");
                }else if ((today.equals("Tue"))||(today.equals("อ."))){

                    for( int i=0;i<tuCount_i;i++) {
                        String time_appointments = date2 + " " + start_time_tue.get(i);
                        String lat_long = loca_tue.get(i);
                        String time_notice = noti_tue.get(i);
                        Log.i("lat_long", lat_long);
                        Log.i("time_appointments", String.valueOf(time_appointments));
                        Thread x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();
                    }

                    Log.i("Pancake ", "Tuesday");
                }else if ((today.equals("Wed"))||(today.equals("พ."))){
                    for( int i=0;i<wCount_i;i++) {
                        String time_appointments = date2 + " " + start_time_wed.get(i);
                        String lat_long = loca_wed.get(i);
                        String time_notice = noti_wed.get(i);
                        Log.i("time_notice ", time_notice);
                        Thread x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();
                    }
                    Log.i("Pancake ", "Wednesday");
                }else if ((today.equals("Thu"))||(today.equals("พฤ."))) {
                    for( int i=0;i<thCount_i;i++) {
                        String time_appointments = date2 + " " + start_time_thu.get(i);
                        String lat_long = loca_thu.get(i);
                        String time_notice = noti_thu.get(i);
                        Log.i("time_notice ", time_notice);
                        Thread x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();
                    }
                    Log.i("Pancake ", "Thursday");
                }else if ((today.equals("Fri"))||(today.equals("ศ."))) {
                     for( int i=0;i<fCount_i;i++) {
                    String time_appointments = date2 + " " + start_time_fri.get(i);
                    String lat_long = loca_fri.get(i);
                    String time_notice = noti_fri.get(i);
                    Log.i("time_notice ", time_notice);
                     x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                    x.start();
                }
                    Log.i("Pancake ", "Friday");
                }else if ((today.equals("Sat"))||(today.equals("ส."))) {
                    for( int i=0;i<saCount_i;i++) {
                        String time_appointments = date2 + " " + start_time_sat.get(i);
                        String lat_long = loca_sat.get(i);
                        String time_notice = noti_sat.get(i);
                        Log.i("time_notice ", time_notice);
                        x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();
                    }
                    Log.i("Pancake ", "Saturday");
                }else if((today.equals("Sun"))||(today.equals("อา."))) {
                    Log.i("suCount_i", String.valueOf(suCount_i));
                    for( int i=0;i<suCount_i;i++) {
                        String time_appointments = date2 + " " + start_time_sun.get(i);
                        String lat_long = loca_sun.get(i);
                        String time_notice = noti_sun.get(i);
                        Log.i("time_notice ", time_notice);
                        Thread x = new Thread(new CalAlertTime(time_appointments,lat_long,time_notice));
                        x.start();
                    }

                    Log.i("Pancake ", "Sunday");
                }else
                    Log.i("Pancake ", "Not day");


                //Log.i("mooho na ", "finish2");
//            switch (today){
//                case "Mon":
//                    ;
//                    ;
//                    break;
//                case "Tue":
//                    ;
//                    ;
//                    break;
//                case ""
//
//            }


        }else
            Log.i("yes","not finish");
    }


    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }



    //ปุ่มตั้งค่าด้านบน
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this,Setting.class);
        this.startActivity(intent);
        return false;
    }
//    private void testDate() {
//
//
//
//    }
//
//    private void testAddValue() {
////        objSemester.addNewSemester("testSemester",);
//        objSemester.addNewSemester("one","two","2","3");
//        objClass_Schedule.addNewClass_Schedule("mint", "mint", "mint", "testStartTime", "testDetail");
//        objLocation.addNewLocation("benz", "testLat", "testLng");
//    }

    private void connectedSQLite() {
        //connectSQLite
//        objSemester = new Semester(this);
        objSemester = new Semester(this);
        objClass_Schedule = new Class_Schedule(this);
        objLocation = new Location(this);
    }
//getStartdate,enddate
    public ArrayList<String> getAllsemList() {
//        db = new MySQLiteOpenHelper(context);
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Semester ;", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            // String str = cursor.getString(3)  + cursor.getString(4);
            start_date = cursor.getString(3);
            end_date =(cursor.getString(4));
            Log.i(" mint start,end", start_date);
            Log.i("mint start,end", end_date);
            // todoList.add(str);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }
    //getDatabase--------------------------------------
    public ArrayList<String> getAllclassList() {
//        db = new MySQLiteOpenHelper(context);
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule ;", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            days = cursor.getString(3);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            Log.i("dayny ", days);
             todoList.add(days);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllMonList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Mon';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

         // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
           // Log.i("Monny ", Monny);
           // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            mCount++;
            cursor.moveToNext();
        }
        mCount_i=mCount;
         Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }
    public ArrayList<String> getAllTueList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Tue';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            tuCount++;
            cursor.moveToNext();
        }
        tuCount_i=tuCount;
    //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllWList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Wed';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            wCount++;
            cursor.moveToNext();
        }
        wCount_i=wCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllThuList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Mon';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            thCount++;
            cursor.moveToNext();
        }
        thCount_i=thCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllFriList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Fri';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            fCount++;
            cursor.moveToNext();
        }
        fCount_i=fCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllSatList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Sat';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            saCount++;
            cursor.moveToNext();
        }
        saCount_i=saCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllSunList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Sun';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(4);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
             Log.i("todoList ", String.valueOf(todoList));
            suCount++;
            cursor.moveToNext();
        }
        suCount_i=suCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

//    public ArrayList<String> getLocaMonList() {
//        data = db.getWritableDatabase();
//        ArrayList<String> todoList = new ArrayList<String>();
//        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id ", null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
//
//            // String  Monny = cursor.getString(3);
//            String  strM = cursor.getString(4);
//            // end_date =(cursor.getString(4));
//            // Log.i(" mint start,end", start_date);
//            // Log.i("Monny ", Monny);
//            // Log.i("Monn2 ", Monny2);
//            todoList.add(strM);
//            Log.i("todoList ", String.valueOf(todoList));
//            suCount++;
//            cursor.moveToNext();
//        }
//        suCount_i=suCount;
//        //    Log.i("mCount ", String.valueOf(mCount));
//        cursor.close();
//        return todoList;
//    }

    //get location lat long
    public ArrayList<String> getAllLocalMonList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Mon';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

             String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

             Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }
    public ArrayList<String> getAllLocalTueList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Tue';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        tuCount_i=tuCount;
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllLocalWList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Wed';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllLocalThuList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Mon';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllLocalFriList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Fri';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllLocalSatList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Sat';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllLocalSunList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule INNER JOIN Location ON Class_schedule.location=Location._id where day='Sun';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String  strM1 = cursor.getString(12);
            String  strM2 = cursor.getString(11);
            String strLatLng = strM1+","+strM2;

            Log.i("strLatLng ", strLatLng);
            todoList.add(strLatLng);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiMonList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Mon';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);

            cursor.moveToNext();
        }

        Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }
    public ArrayList<String> getAllNotiTueList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Tue';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);

            cursor.moveToNext();
        }

        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiWList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Wed';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
          //  Log.i("strMpppppppppppppppppppppppppppppppppp ", strM);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);

            cursor.moveToNext();
        }

        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiThuList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Mon';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);

            cursor.moveToNext();
        }

        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiFriList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Fri';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);

            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiSatList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Sat';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getAllNotiSunList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Sun';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // String  Monny = cursor.getString(3);
            String  strM = cursor.getString(6);
            // end_date =(cursor.getString(4));
            // Log.i(" mint start,end", start_date);
            // Log.i("Monny ", Monny);
            // Log.i("Monn2 ", Monny2);
            todoList.add(strM);
            Log.i("todoList ", String.valueOf(todoList));
            cursor.moveToNext();
        }
        //    Log.i("mCount ", String.valueOf(mCount));
        cursor.close();
        return todoList;
    }
}
