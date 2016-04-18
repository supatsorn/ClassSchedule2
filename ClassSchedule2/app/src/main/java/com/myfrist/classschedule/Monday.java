package com.myfrist.classschedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Monday extends AppCompatActivity {
    ListView listView1;
    SQLiteDatabase mDb;
    MySQLiteOpenHelper mHelper;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Class_Schedule objClass_Schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        //display listview
        mHelper = new MySQLiteOpenHelper(this);
        mDb = mHelper.getWritableDatabase();

        //connected SQLite
        connectedSQLite();
        db = new MySQLiteOpenHelper(Monday.this);
        data = db.getWritableDatabase();
        db.close();

//        mCursor = mDb.rawQuery("SELECT * FROM Class_schedule", null);
//        ArrayList<String> dirArray = new ArrayList<String>();
//
//        mCursor.moveToFirst();
//        while ( !mCursor.isAfterLast() ) {
//            dirArray.add(mCursor.getString(mCursor.getColumnIndex
//                    (Class_Schedule.SBJ_NAME)));
//            mCursor.moveToNext();
//        }
//
//
//        listView1.setAdapter(new ArrayAdapter(this
//                , android.R.layout.simple_list_item_1, dirArray));
//        ArrayList<String> myList = mClass.getAllSemList();
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.SBJ_NAME));
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.SBJ_CODE));
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.DAY));
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.START_TIME));
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.LOCATION));
////        mCursor.getString(mCursor.getColumnIndex(Class_Schedule.DETAIL));

        listView1 = (ListView)findViewById(R.id.listView1);
        objClass_Schedule = new Class_Schedule(Monday.this);
        ArrayList<String> myList = getAllList();//สร้าง อาเรริส เราแค่ดึงข้อมูลทั้งหมดมาใช้ เราสร้างไว้แล้ว
        //สร้างอาร์เรย์อะเด็ปเตอร์
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        listView1.setAdapter(adapter);//เอา adepter ไปใส่ใน todoListView



    }
    public ArrayList<String> getAllList() {
//        db = new MySQLiteOpenHelper(context);
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Mon';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String str = "วิชา"+cursor.getString(1) + "  : เวลา" + cursor.getString(4)
                         +"  : สถานที่"+ cursor.getString(5)  ;
//            String str = cursor.getString(3);
//            todoList.add(cursor.getString(1));
           todoList.add(str);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }
    private void connectedSQLite() {
        objClass_Schedule = new Class_Schedule(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_mon_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this,AddMonday.class);
        this.startActivity(intent);
        return false;
    }

}
