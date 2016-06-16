package com.ychornyi.seasontracker.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ychornyi.seasontracker.model.items.SeriesItem;

/**
 * Created by y.chornyi on 15.06.2016.
 */
public class dbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "main.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SERIALS_TABLE_NAME = "serials";
    private static final String SERIALS_COLUMN_ID = "_id";
    private static final String SERIALS_COLUMN_NAME = "name";
    private static final String SERIALS_COLUMN_LAST_UPDATE = "last_update";



    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ SERIALS_TABLE_NAME +" ("+
        SERIALS_COLUMN_ID+" integer primary key autoincrement, "+
        SERIALS_COLUMN_NAME+" text not null, "+
        SERIALS_COLUMN_LAST_UPDATE+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ SERIALS_TABLE_NAME);
        onCreate(db);
    }

    public boolean addSeries (SeriesItem series){
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String[] columns = null;
        columns = new String[]{ SERIALS_COLUMN_NAME };
        cursor = db.query(SERIALS_TABLE_NAME,columns,null,null,null,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                do {
                    if (cursor.getString(cursor.getColumnIndex(SERIALS_COLUMN_NAME)).toLowerCase().equals(series.getName().toLowerCase())){
                        flag = true;
                        break;
                    }
                } while (cursor.moveToNext());
            }
        }
        if (flag){

        }else{

        }
        return !flag;

    }
}
