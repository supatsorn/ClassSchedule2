package com.myfrist.classschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Minnyclash on 9/2/2559.
 */
public class Class_Schedule {

    private Context context;
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private MySQLiteOpenHelper db;
    private SQLiteDatabase data;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String CLASS_TABLE = "Class_schedule";
    public static final String CLASS_ID= "class_id";
    public static final String SBJ_NAME = "sbj_name";
    public static final String SBJ_CODE = "sbj_code";
    public static final String DAY = "day";
    public static final String START_TIME = "start_time";
    public static final String LOCATION = "location";
    public static final String NOTICE = "notice";
    public static final String DETAIL = "detail";

    public Class_Schedule(Context context) {
        this.context = context;
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }   // Constructor
    public boolean addNewClass_Schedule(String strName, String Code,String Day,String startTime,int Location,String Notice,String Detail){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(SBJ_NAME,strName);
        objContentValues.put(SBJ_CODE,Code);
        objContentValues.put(DAY,Day);
        objContentValues.put(START_TIME,startTime);
        objContentValues.put(LOCATION,Location);
        objContentValues.put(NOTICE,Notice);
        objContentValues.put(DETAIL,Detail);
        Log.d("Todo List Demo ::: ", "Add OK!!!");
        long result = writeSqLiteDatabase .insert(CLASS_TABLE,null,objContentValues);
        if (result == -1){
            return false;
        }else
            return true;
    }
    public void deleteDClass(Integer id) {
        SQLiteDatabase db = objMySQLiteOpenHelper.getWritableDatabase();
        db.delete(CLASS_TABLE, "class_id="+id, null);
    }


//    public ArrayList<String> getAllSemList() {
//        db = new MySQLiteOpenHelper(context);
//        data = db.getWritableDatabase();
//        ArrayList<String> todoList = new ArrayList<String>();
//        Cursor cursor = data.rawQuery("SELECT * from Class_schedule ORDER BY class_id DESC LIMIT 1;", null);
//
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
////            String str = cursor.getString(1) + "," + cursor.getString(2) + "," + cursor.getString(3)
////                         +","+ cursor.getString(4) + "," + cursor.getString(5) + "," + cursor.getString(6)
////                         + "," + cursor.getString(6);
//             String str = cursor.getString(3);
//             todoList.add(cursor.getString(3));
//            //todoList.add(str);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return todoList;
//    }

}
