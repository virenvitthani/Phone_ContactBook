package com.example.phone_contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context,"contacts",null,1);
        Log.d("TTT", "Database: Database Create");;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "create table Contact(Id integer primary key autoincrement,Name text,NUMBER text,IMAGEPATH text)";
        sqLiteDatabase.execSQL(query);
        Log.d("TTT", "onCreate: Table Create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addContacts(String name, String number, String imagepath) {
        String query="insert into Contact (NAME,NUMBER,IMAGEPATH) values('"+name+"','"+number+"','"+imagepath+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
        Log.d("TTT", "addContacts: name = "+name);
        Log.d("TTT", "addContacts: number = "+number);
    }

    public void deletecontacts(int id) {
        String query="delete from contact where Id="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

    public void updatecontacts(int id, String name, String number) {
        String query="update Contact set NAME='"+name+"',NUMBER='"+number+"' where Id="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor Displayed() {
        String query="select * from Contact";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }
}
