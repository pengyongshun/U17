package com.example.u17.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        helper = new MyDatabaseHelper(this.getContext(),null);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("searchInfo", null, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = helper.getReadableDatabase();
        db.insert("searchInfo",null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db=helper.getReadableDatabase();
        int num = db.delete("searchInfo", selection, selectionArgs);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db=  helper.getReadableDatabase();
        int num = db.update("searchInfo", values, selection, selectionArgs);
        return num;
    }
}
