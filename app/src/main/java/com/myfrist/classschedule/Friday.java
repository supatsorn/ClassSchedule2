package com.myfrist.classschedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Friday extends AppCompatActivity {

    ListView listView5;
    SQLiteDatabase tDb;
    MySQLiteOpenHelper tHelper;
    private SQLiteDatabase data;
    private MySQLiteOpenHelper db;
    private Class_Schedule objClass_Schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);

        //display listview
        tHelper = new MySQLiteOpenHelper(this);
        tDb = tHelper.getWritableDatabase();

        //connected SQLite
        connectedSQLite();
        db = new MySQLiteOpenHelper(Friday.this);
        data = db.getWritableDatabase();
        db.close();

        //display Listview2
        createListView();
//        listView5 = (ListView)findViewById(R.id.listView5);
//        objClass_Schedule = new Class_Schedule(Friday.this);
//        ArrayList<String> myList = getAllList();//สร้าง อาเรริส เราแค่ดึงข้อมูลทั้งหมดมาใช้ เราสร้างไว้แล้ว
//        //สร้างอาร์เรย์อะเด็ปเตอร์
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
//        listView5.setAdapter(adapter);//เอา adepter ไปใส่ใน todoListView
//        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 deleteListView(position);
//            }
//        });
    }
    private void createListView(){
        listView5 = (ListView)findViewById(R.id.listView5);
        objClass_Schedule = new Class_Schedule(Friday.this);
        final ArrayList<String> myList = getAllList();//สร้าง อาเรริส เราแค่ดึงข้อมูลทั้งหมดมาใช้ เราสร้างไว้แล้ว
        //สร้างอาร์เรย์อะเด็ปเตอร์
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        listView5.setAdapter(adapter);//เอา adepter ไปใส่ใน todoListView
        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myList.get(position);
//                getID id the getter method of shoppingdata ,here you can declare your method for ID as whatever you have in shopping data
//                tDb.delete((int)myList.get(position);
//                return true;
                deleteListView(position);
            }
        });
    }
    private void deleteListView(final int position){
        AlertDialog.Builder objBuilder=new AlertDialog.Builder(this);
        objBuilder.setTitle("Are you sure?");
        objBuilder.setMessage("Delete this class ");
        objBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createListView();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createListView();
            }
        });
        objBuilder.show();
    }
    private void connectedSQLite() {
        objClass_Schedule = new Class_Schedule(this);
    }

    public ArrayList<String> getAllList() {
        data = db.getWritableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = data.rawQuery("SELECT * from Class_schedule where day='Fri';", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String local = cursor.getString(5);
            Log.i("Local is ", local);
            if(local.equals("1")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : SCB1"+cursor.getString(5)+"  Note! : "+ cursor.getString(7) ;
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("2")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : SCB2"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("3")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : SCB3"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("4")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : SCB4"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("5")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : MB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("6")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : STB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("7")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : PB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("8")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : IMB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("9")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : CB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("10")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : BB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }else if(local.equals("11")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : GB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }
            else if(local.equals("12")){
                String str = "วิชา : "+cursor.getString(1) + "   เวลา :   " + cursor.getString(4)+"  สถานที่ : CSB"+cursor.getString(5)+"  Note! : "+ cursor.getString(7);
                todoList.add(str);
                cursor.moveToNext();
            }
            String strt = "วิชา : Not Found " + "   เวลา : Not Found ";
        }
        cursor.close();
        return todoList;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_fri_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this,AddFriday.class);
        this.startActivity(intent);
        return false;
    }
}
