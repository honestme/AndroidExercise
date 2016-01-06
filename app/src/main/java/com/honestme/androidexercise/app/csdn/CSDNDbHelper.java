package com.honestme.androidexercise.app.csdn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhangconglin on 2016/1/5.
 */
public class CSDNDbHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "CSDN";

    public static final int VERSION = 1;

    public static final String TABLE_NEWS_TITLE = "csdnNewsTitle";

    public static final String CREATE_TABLE_NEWS_TITLE = "CREATE TABLE " + TABLE_NEWS_TITLE + "(" + "" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,contentLink TEXT,date TEXT,imageLink TEXT,content TEXT," +
            "viewTimes TEXT,recommends TEXT,newsType INTEGER";

    public CSDNDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NEWS_TITLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion){
            case 1:
                onCreate(db);
        }
    }


}
