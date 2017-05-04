package com.example.sony.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper{

    private static final String DataBase_Name="contactinfo.db";
    private static final int DataBase1_Version=1;
    private static final String Table_Name="contactdetails";

    private SQLiteDatabase sqldb;


        public DataHelper(Context context) {
            super(context, DataBase_Name, null, DataBase1_Version);
        }

    public void insertContact(contacts c){
            sqldb=this.getWritableDatabase();

            ContentValues value = new ContentValues();
            value.put("name", c.getName());
            value.put("mail", c.getEmail());
            value.put("password", c.getPass());

            sqldb.insertOrThrow(Table_Name, null, value);
            sqldb.close();
    }

    public ArrayList<String> fetchData(){
        ArrayList<String> aa= new ArrayList<String>();
        sqldb=this.getWritableDatabase();
        Cursor c=sqldb.query(Table_Name,null,null,null,null,null,null);

    if (c.moveToFirst()) {
        while (c.isAfterLast() == false) {
            String a = (c.getString(0) + " | " + c.getString(1) + " | " + c.getString(2));
            aa.add(a);
            c.moveToNext();
        }
    }

        if( c!=null && !c.isClosed()){
            c.close();
        }
        sqldb.close();
        return aa;
    }

    public void deleteAll(){
        sqldb=this.getWritableDatabase();
        sqldb.execSQL("Delete from "+Table_Name);
        sqldb.close();
    }

    public String searchPass(String name){
    sqldb=this.getReadableDatabase();
        String a,b;
        b="not found";
    Cursor c=sqldb.query(Table_Name,null,null,null,null,null,null);
    if(c.moveToFirst()){
    do{
        a=c.getString(0);
        if(a.equals(name)) {
            b = c.getString(2);
            break;
        }
    }while(c.moveToNext());
    }
    return b;

    }

       @Override
       public void onCreate(SQLiteDatabase sqldb) {
           sqldb.execSQL("Create Table "+Table_Name+"(Name Text primary key,mail Text,Password Text)");
       this.sqldb=sqldb;
       }

       @Override
       public void onUpgrade(SQLiteDatabase sqldb, int i, int i1) {
        sqldb.execSQL("drop if exists "+Table_Name);
           this.onCreate(sqldb);
       }
   }
