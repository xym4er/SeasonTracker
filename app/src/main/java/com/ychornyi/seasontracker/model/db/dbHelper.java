package com.ychornyi.seasontracker.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ychornyi.seasontracker.Utils;
import com.ychornyi.seasontracker.model.items.SeriesItem;

public class dbHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "main.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SERIALS_TABLE_NAME = "serials";
    private static final String SERIALS_COLUMN_ID = "_id";
    private static final String SERIALS_COLUMN_NAME = "name";
    private static final String SERIALS_COLUMN_TABLE_NAME = "name";
    private static final String SERIALS_COLUMN_LAST_UPDATE = "last_update";
    private static final String SERIALS_COLUMN_PICTURE = "last_update";

    private static final String FILM_COLUMN_ID = "_id";
    private static final String FILM_COLUMN_NAME = "name";
    private static final String FILM_COLUMN_TRANSLATE = "translate";
    private static final String FILM_COLUMN_SEASON = "season";
    private static final String FILM_COLUMN_SERIA = "seria";
    private static final String FILM_COLUMN_URL = "url";
    private static final String FILM_COLUMN_DATE = "date";
    private static final String FILM_COLUMN_FILMID = "filmid";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ SERIALS_TABLE_NAME +" ("+
        SERIALS_COLUMN_ID+" integer primary key autoincrement, "+
        SERIALS_COLUMN_NAME+" text not null, "+
        SERIALS_COLUMN_TABLE_NAME+" text, "+
        SERIALS_COLUMN_PICTURE+" text, "+
        SERIALS_COLUMN_LAST_UPDATE+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ SERIALS_TABLE_NAME);
        onCreate(db);
    }

    public boolean mkNewTable (String tableName,SQLiteDatabase db){
        db.execSQL("create table "+ tableName +" ("+
                FILM_COLUMN_ID+" integer primary key autoincrement, "+
                FILM_COLUMN_NAME+" text not null, "+
                FILM_COLUMN_TRANSLATE+" text, "+
                FILM_COLUMN_SEASON+" text, "+
                FILM_COLUMN_SERIA+" text, "+
                FILM_COLUMN_URL+" text, "+
                FILM_COLUMN_DATE+" text, "+
                FILM_COLUMN_FILMID+" text);");

        return false;
    }

    private ContentValues mkValues (SeriesItem series){
        ContentValues result = new ContentValues();
        result.put(FILM_COLUMN_NAME,series.getName());
        result.put(FILM_COLUMN_TRANSLATE,series.get);
        result.put(FILM_COLUMN_NAME,series.getName());

        return result;
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
            db.insert(Utils.encode(series.getName()),);

        }else{
            mkNewTable(Utils.encode(series.getName()),db);
        }
        return flag;

    }
}
