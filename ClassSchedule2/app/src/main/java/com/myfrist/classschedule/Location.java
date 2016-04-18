package com.myfrist.classschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Minnyclash on 9/2/2559.
 */
public class Location {


    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;

    public static final String Location_TABLE = "Location";
    public static final String LOCATION_ID = "location_id";
    public static final String LOCATION_NAME = "location_name";
    public static final String LAT = "lat";
    public static final String LNG = "lng";


    private Context context;
    private double longitude;

    public Location(Context context) {
        this.context = context;
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }   // Constructor


    public boolean addNewLocation(String strLocation_Name, String strLat, String strLng) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(LOCATION_NAME, strLocation_Name);
        objContentValues.put(LAT, strLat);
        objContentValues.put(LNG, strLng);
        long result = writeSqLiteDatabase.insert(Location_TABLE, null, objContentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }


    public ArrayList<String> getAllList() {
        db = new MySQLiteOpenHelper(context);
        data = db.getWritableDatabase();
        // db.close();
        ArrayList<String> mintList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * FROM Location;", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            mintList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        return mintList;
    }


    public double getLongitude() {
        return longitude;
    }

}