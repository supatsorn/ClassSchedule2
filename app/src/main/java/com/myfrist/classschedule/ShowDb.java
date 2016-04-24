package com.myfrist.classschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Minnyclash on 16/2/2559.
 */
public class ShowDb {



        private SQLiteDatabase database;
        private com.myfrist.classschedule.MySQLiteOpenHelper dbHelper;

        public ShowDb(Context context) {
            dbHelper = new com.myfrist.classschedule.MySQLiteOpenHelper(context);
        }
        public void open(){
            database = dbHelper.getWritableDatabase();
        }
        public  void close(){
            dbHelper.close();
        }
        public ArrayList<String> getAllMintList(){
            ArrayList<String> todoList = new ArrayList<String>();
            Cursor cursor = database.rawQuery("SELECT * FROM Semester;",null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){

                todoList.add(cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
            return todoList;
        }

//        public void  add(TodoList todoList){
//            TodoList newTodoList = new TodoList();
//            newTodoList = todoList;
//
//            //get ค่า เข้ามาเก็บไว้ใน contentValues
//            ContentValues values = new ContentValues();
//            values.put("mint",newTodoList.getTodoText());
//            //ใส่ข้อมูลลงไปในดาต้าเบส ชื่อ todo_list
//            this.database.insert("mint",null,values);
//            Log.d("Todo List Demo ::: ", "Add OK!!!");
//
//        }


}
