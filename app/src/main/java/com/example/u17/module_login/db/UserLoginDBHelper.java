package com.example.u17.module_login.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/14
 */
public class UserLoginDBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;

    /**
     * SQLiteOpenHelper构造函数：构造函数执行时，只是连接上数据库，
     * 调用了getWritableDatabase或者getReadableDatabase方法，才是真正的创建数据库
     *
     * @param context 上下文
     * @param name 数据库文件名字
     * @param factory 管理游标的工厂对象，一般不会使用，可以设置成null
     * @param version 数据库的版本号
     */
    public UserLoginDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mDb = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建UserLogin表
        db.execSQL("CREATE TABLE IF NOT EXISTS user_login (id integer PRIMARY KEY AUTOINCREMENT, email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
