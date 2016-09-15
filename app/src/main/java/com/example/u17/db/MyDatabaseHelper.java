package com.example.u17.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Comic";
    public static final int VERSION_CODE = 1;
    public static final String CREATE_TB_SEARCH_INFO = "create table searchInfo ( " + "_id integer primary key autoincrement," + "search_name string"+ ");";


    public MyDatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, VERSION_CODE);
    }

    /**
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_SEARCH_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
