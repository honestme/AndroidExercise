package com.honestme.androidexercise.app.csdn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangconglin on 2016/1/6.
 */
public class CSDNNewsDao {
    private CSDNDbHelper mDbHelper;


    public CSDNNewsDao(Context context){
        mDbHelper = new CSDNDbHelper(context,null,null,0);
    }

    public void addNewsItem(CSDNNewsItem item){
        SQLiteDatabase mDatabase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",item.getId());
        values.put("title",item.getTitle());
        values.put("contentLink",item.getContentLink());
        values.put("imageLink",item.getImageLink());
        values.put("date",item.getDate());
        values.put("content",item.getContent());
        values.put("viewTimes",item.getViewTimes());
        values.put("recommends",item.getRecommends());
        values.put("newsType",item.getNewsType());
        mDatabase.insert(CSDNDbHelper.TABLE_NEWS_TITLE,null,values);
        mDatabase.close();
    }

    public void deleteNewsItem(CSDNNewsItem item){
        SQLiteDatabase mDatabase = mDbHelper.getWritableDatabase();
        String[] whereArgs = {"id=" + item.getId()};
        mDatabase.delete(CSDNDbHelper.TABLE_NEWS_TITLE, null, whereArgs);
        mDatabase.close();
    }

    public void deleleAllByType(int newsType){
        SQLiteDatabase mDatabase = mDbHelper.getWritableDatabase();
        String[] whereArgs = {"newsType="+newsType};
        mDatabase.delete(CSDNDbHelper.TABLE_NEWS_TITLE, null, whereArgs);
        mDatabase.close();
    }

    public void addNewsItemList(List<CSDNNewsItem> list){
        for (CSDNNewsItem item:list
             ) {
            addNewsItem(item);
        }
    }

    public List<CSDNNewsItem> listByTypeAndPage(int newsType,int page){
        SQLiteDatabase dataBase = mDbHelper.getReadableDatabase();
        List<CSDNNewsItem> newsList = new ArrayList<CSDNNewsItem>();

        try {
            int offSet = (page - 1) * 10;
            String[] selectionArgs = {"newsType=" + newsType};
            String limit = offSet + "," + (offSet + 10);
            Cursor cursor = dataBase.query(CSDNDbHelper.TABLE_NEWS_TITLE, new String[]{"title",
                    "content","contentLink","imageLink", "viewTimes",
                    "date", "recommends"}, null, selectionArgs, null, null, null, limit);

            while (cursor.moveToNext()){
                CSDNNewsItem item = new CSDNNewsItem();
                item.setTitle(cursor.getString(0));
                item.setContent(cursor.getString(1));
                item.setContentLink(cursor.getString(2));
                item.setImageLink(cursor.getString(3));
                item.setViewTimes(cursor.getString(4));
                item.setDate(cursor.getString(5));
                item.setRecommends(cursor.getString(6));

                newsList.add(item);
            }
            cursor.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {

            dataBase.close();
        }


        return newsList;
    }
}
