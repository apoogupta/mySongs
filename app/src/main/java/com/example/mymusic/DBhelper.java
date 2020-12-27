package com.example.mymusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabase.db";


    public static final String TABLE_NAME = "MySongsList";
    public static final String col1 = "title";
    public static final String col2 = "description";
    public static final String col3 = "lyrics";
    private HashMap hp;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table MySongsList " +
                        "(title text primary key, description text,lyrics text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }


    public boolean insertSong(String s1, String s2, String s3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, s1);
        contentValues.put(col2, s2);
        contentValues.put(col3, s3);
        db.insert("MySongsList", null, contentValues);
        return true;

    }

    public Cursor ShowSongNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from  MySongsList ", null);
        return res;


    }

    public Cursor ShowLyrics(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME +" WHERE Title=? ",new String[]{s} );
      return res;
       // Cursor res = db.rawQuery(" select * from  MySongsList where Title="+s+"", null);
        //return res;

    }
    public boolean DeleteSong(String s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return (db.delete("MySongsList","title=?",new String[]{s}))>0;
    }
}
