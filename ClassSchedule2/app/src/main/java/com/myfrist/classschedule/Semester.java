package com.myfrist.classschedule;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Locale.*;

/**
 * Created by Minnyclash on 9/2/2559.
 */
public class Semester {
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private  Setting objSetting;
    private com.myfrist.classschedule.MySQLiteOpenHelper dbHelper;

    public static final String SEMESTER_TABLE = "Semester";
    public static final String SEMESTER_ID= "semester_id";
    public static final String SEMESTER_NUM = "semester_num";
    public static final String YEAR = "year";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    private Context context;

    public Semester(Context context) {
        this.context = context;
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }   // Constructor
    public void open(){

        SQLiteDatabase database = objMySQLiteOpenHelper.getWritableDatabase();
    }
    public  void close(){

        dbHelper.close();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public String getDateTime(ArrayList<String> dateStr) {
//         String dateStr1 = dateStr.toString();
//        try {
//            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateStr1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//       // Log.e("Mydate", "mint" );
////        SimpleDateFormat dateFormat = new SimpleDateFormat(
////                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
////        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag(dateStr1));
//        Date date = new Date();
//        return dateFormat.format(date);
//    }
    String getDateTime(ArrayList<String> calendar) {
        String calen = calendar.toString();
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(calen);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();


        return dateFormat.format(date);
    }
    public boolean addNewSemester( String strSemesterNum, String strYear,String start_date,String end_date){
     //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     //  Date date = new Date();
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(SEMESTER_NUM,strSemesterNum);
        objContentValues.put(YEAR, strYear);
        objContentValues.put(START_DATE,start_date);
//       // objContentValues.put(START_DATE,System.currentTimeMillis());
        objContentValues.put(END_DATE,end_date);
      //  objContentValues.put(END_DATE, strEndDate);
         long result = writeSqLiteDatabase .insert(SEMESTER_TABLE,null,objContentValues);
        if (result == -1){
            return false;
        }else
            return true;
       // return writeSqLiteDatabase.insert(SEMESTER_TABLE,null,objContentValues);

    }

    public ArrayList<String> getAllSemList(){
        db = new MySQLiteOpenHelper(context);
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Semester ORDER BY semester_id DESC LIMIT 1;",null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
           String str = cursor.getString(0)+","+  cursor.getString(3) +","+  cursor.getString(4);
           // String str = cursor.getString(3);
           // todoList.add(cursor.getString(3));
            todoList.add(str);
            cursor.moveToNext();
        }
//        cursor = data.rawQuery("SELECT MAX(semester_id) FROM Semester;",null);
//        String test = cursor.getString(3);
        cursor.close();
        return todoList;
    }



//
//    public long addNewFood(String strFood, String strSource, String strPrice) {
//        ContentValues objContentValues = new ContentValues();
//        objContentValues.put(COLUMN_FOOD, strFood);
//        objContentValues.put(COLUMN_SOURCE, strSource);
//        objContentValues.put(COLUMN_PRICE, strPrice);
//        return readSqLiteDatabase.insert(FOOD_TABLE, null, objContentValues);
//    }   // addNewFood
}
