package com.myfrist.classschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Minnyclash on 9/2/2559.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public SQLiteDatabase database;

    private com.myfrist.classschedule.MySQLiteOpenHelper dbHelper;
    private static final String databaseName = "mint29.sqlite";
    private  static  final  int databaseVertion = 1;
    Context myContext;

    private static  final String TableSemester = "CREATE TABLE Semester" +
            "(semester_id INTEGER PRIMARY KEY AUTOINCREMENT,semester_num TEXT,year TEXT,start_date TEXT,end_date TEXT);";
    private static final String TableClassSchedule = "CREATE TABLE Class_schedule"+
            "(class_id INTEGER PRIMARY KEY AUTOINCREMENT,sbj_name TEXT,sbj_code TEXT,day TEXT,start_time TEXT,location INTEGER,notice TEXT,detail TEXT,location_id INTEGER);";
//    private static final String TableLocation = "CREATE TABLE Location"+
//            "(location_id INTEGER PRIMARY KEY AUTOINCREMENT,location_name TEXT,lat INTEGER,lng INTEGER);";

  //  https://maps.googleapis.com/maps/api/distancematrix/json?origins=18.8016565,98.9547273&destinations=18.8011958,98.9532585&mode=walking&language=fr-FR&avoid=tolls&key=AIzaSyAtjcnHYDIXpoxUKBHa62x0KVgVGvkdrV8
    public static final String Location_TABLE = "Location";
    public static final String LOCATION_ID= "location_id";
    public static final String LOCATION_NAME = "location_name";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public MySQLiteOpenHelper(Context context) {
        super(context,databaseName, null, databaseVertion);
        this.myContext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableSemester);
        db.execSQL(TableClassSchedule);                                      //yyyy-MM-dd HH:mm:ss
       // db.execSQL(TableLocation);

        db.execSQL("CREATE TABLE "+ Location_TABLE
                +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOCATION_NAME + " TEXT, " + LNG + " TEXT, " + LAT + " TEXT );");
        db.execSQL("INSERT INTO Location (location_name, lng, lat )VALUES ('SCB1','18.8015272','98.9511638');");
        db.execSQL("INSERT INTO Location (location_name,lat,lng) VALUES ('SCB2','18.8016565','98.9547273');");
        db.execSQL("INSERT INTO Location (location_name,lat,lng) VALUES ('SCB3','18.8032285','98.952047');") ;
        db.execSQL( "INSERT INTO Location (location_name,lat,lng) VALUES ('SCB4','18.803145','98.953478');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('MB','18.8031471','98.9525578');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('STB','18.8034241','98.9521138');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('PB','18.8011958','98.9532585');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('IMB','18.8029354','98.9547791');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('CB','18.8020137','98.9517463');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('BB','18.8025151','98.9508895');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('GB','18.8032379','98.9504395');");
        db.execSQL ("INSERT INTO Location (location_name,lat,lng) VALUES ('CSB','18.8032379','98.9504395');");
        //String insertData2 = "INSERT INTO mint (todo_text) VALUES ('Todo Text 2');";
//        String insertData3 = "INSERT INTO mint (todo_text) VALUES ('Todo Text 3');";
//        String insertData4 = "INSERT INTO mint (todo_text) VALUES ('Hi mint 4');";
//        String insertData5 = "INSERT INTO mint (todo_text) VALUES ('Hi mint 5');";
//        String insertData6 = "INSERT INTO mint (todo_text) VALUES ('Hi mint 6 ');";
//        db.execSQL(insertSCB1);
//        db.execSQL(insertSCB2);
//        db.execSQL(insertSCB3);
//        db.execSQL(insertSCB4);
//        db.execSQL(insertMB);
//        db.execSQL(insertSTB);
//        db.execSQL(insertPB);
//        db.execSQL(insertIMB);
//        db.execSQL(insertCB);
//        db.execSQL(insertBB);
//        db.execSQL(insertGB);
//        db.execSQL(insertCSB);

    }


//    public ArrayList<String> getAllMintList(){
//        ArrayList<String> mintList = new ArrayList<String>();
//        Cursor cursor = database.rawQuery("SELECT * FROM Location;",null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()){
//
//            mintList.add(cursor.getString(1));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return mintList;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableSemester);
        onCreate(db);
    }



}
