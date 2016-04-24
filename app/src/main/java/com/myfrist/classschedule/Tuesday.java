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

import java.util.ArrayList;

public class Tuesday extends AppCompatActivity {

    ListView listView2;
    SQLiteDatabase tDb;
    MySQLiteOpenHelper tHelper;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Class_Schedule objClass_Schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);

        //display listview
        tHelper = new MySQLiteOpenHelper(this);
        tDb = tHelper.getWritableDatabase();

        //connected SQLite
        connectedSQLite();
        db = new MySQLiteOpenHelper(Tuesday.this);
        data = db.getWritableDatabase();
        db.close();

        //display Listview2
        listView2 = (ListView)findViewById(R.id.listView2);
        objClass_Schedule = new Class_Schedule(Tuesday.this);
        ArrayList<String> myList = getAllList();//สร้าง อาเรริส เราแค่ดึงข้อมูลทั้งหมดมาใช้ เราสร้างไว้แล้ว
        //สร้างอาร์เรย์อะเด็ปเตอร์
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        listView2.setAdapter(adapter);//เอา adepter ไปใส่ใน todoListView

    }

    private void connectedSQLite() {
        objClass_Schedule = new Class_Schedule(this);
    }

    public ArrayList<String> getAllList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Tue';", null);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_tues_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this,AddTuesday.class);
        this.startActivity(intent);
        return false;
    }
}
