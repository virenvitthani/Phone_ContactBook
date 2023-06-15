package com.example.phone_contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context,"contactsBook",null,1);
        Log.d("TTT", "Database: Database Create");;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "create table Contact(Id integer primary key autoincrement,Name text,NUMBER text)";
        sqLiteDatabase.execSQL(query);
        Log.d("TTT", "onCreate: Table Create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addContacts(String name, String number) {
        String query="insert into Contact (NAME,NUMBER) values('"+name+"','"+number+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
        Log.d("TTT", "addContacts: name = "+name);
        Log.d("TTT", "addContacts: number = "+number);
    }

//    public void deletecontacts(int id) {
//        String query="delete from context where Id="+id+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }
//
//    public void updatecontacts(int id, String name, String number) {
//        String query="update Context set NAME='"+name+"',NUMBER='"+number+"',where ID="+id+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }
//
    public Cursor Displayed() {
        String query="select * from Contact";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }
}
