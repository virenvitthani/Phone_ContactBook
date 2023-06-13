package com.example.phone_contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context,"contextBook",null,1);
        Log.d("TTT", "Database: Database Create");;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "create table Context(Id integer primary key autoincrement,Name text,NUMBER text)";
        sqLiteDatabase.execSQL(query);
        Log.d("TTT", "onCreate: Table Create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addContact(String name, String number) {
        String query="insert into Context (NAME,NUMBER) values('"+name+"','"+number+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

//    public void deletecontact(int id) {
//        String query="delete from context where Id="+id+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }
//
//    public void updatecontact(int id, String name, String number) {
//        String query="update Context set NAME='"+name+"',NUMBER='"+number+"',where ID="+id+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }

//    public Cursor displaycontact() {
//        String query="select * from context";
//        SQLiteDatabase db=getReadableDatabase();
//        Cursor cursor= db.rawQuery(query,null);
//        return cursor;
//    }

}
