package com.ychornyi.seasontracker.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ychornyi.seasontracker.Utils.CryptUtils;
import com.ychornyi.seasontracker.model.items.SeriesItem;

public class dbHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "main.db";
    public static final int DATABASE_VERSION = 1;

    private static final String FILMS_TABLE_NAME = "serials";
    private static final String FILMS_COLUMN_ID = "_id";
    private static final String FILMS_COLUMN_NAME = "name";
    private static final String FILMS_COLUMN_TABLE_NAME = "table_name";
    private static final String FILMS_COLUMN_LAST_UPDATE = "last_update";
    private static final String FILMS_COLUMN_PICTURE = "picture";

    private static final String SERIES_COLUMN_ID = "_id";
    private static final String SERIES_COLUMN_NAME = "name";
    private static final String SERIES_COLUMN_TRANSLATE = "translate";
    private static final String SERIES_COLUMN_SEASON = "season";
    private static final String SERIES_COLUMN_SERIA = "seria";
    private static final String SERIES_COLUMN_URL = "url";
    private static final String SERIES_COLUMN_DATE = "date";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FILMS_TABLE_NAME + " (" +
                FILMS_COLUMN_ID + " integer primary key autoincrement, " +
                FILMS_COLUMN_NAME + " text not null, " +
                FILMS_COLUMN_TABLE_NAME + " text, " +
                FILMS_COLUMN_PICTURE + " text, " +
                FILMS_COLUMN_LAST_UPDATE + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + FILMS_TABLE_NAME);
        onCreate(db);
    }

    public boolean mkNewTable(SeriesItem series, SQLiteDatabase db) {
        Log.d("MyTag", "mkNewTable: "+series.getName()+" - "+ CryptUtils.encode(series.getName()));

        db.execSQL("create table " + CryptUtils.encode(series.getName()) + " (" +
                SERIES_COLUMN_ID + " integer primary key autoincrement, " +
                SERIES_COLUMN_NAME + " text not null, " +
                SERIES_COLUMN_TRANSLATE + " text, " +
                SERIES_COLUMN_SEASON + " text, " +
                SERIES_COLUMN_SERIA + " text, " +
                SERIES_COLUMN_URL + " text, " +
                SERIES_COLUMN_DATE + " text);");
        ContentValues cv = new ContentValues();
        Log.d("MyTag", "mkNewTable: "+series.getName()+" - "+ CryptUtils.encode(series.getName()));
        cv.put(FILMS_COLUMN_NAME, series.getName());
        cv.put(FILMS_COLUMN_TABLE_NAME, CryptUtils.encode(series.getName()));
        cv.put(FILMS_COLUMN_PICTURE, "нету");
        cv.put(FILMS_COLUMN_LAST_UPDATE, series.getDate());
        db.insert(FILMS_TABLE_NAME, null, cv);

        return true;
    }

    private ContentValues mkValues(SeriesItem series) {
        ContentValues result = new ContentValues();
        result.put(SERIES_COLUMN_NAME, series.getName());
        result.put(SERIES_COLUMN_TRANSLATE, series.getTranslate());
        result.put(SERIES_COLUMN_SEASON, series.getSeason());
        result.put(SERIES_COLUMN_DATE, series.getDate());
        result.put(SERIES_COLUMN_SERIA, series.getSeria());
        result.put(SERIES_COLUMN_URL, series.getUrl());

        return result;
    }

    public boolean addSeries(SeriesItem series) {
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        String[] columns;
        columns = new String[]{FILMS_COLUMN_NAME};
        cursor = db.query(FILMS_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(cursor.getColumnIndex(FILMS_COLUMN_NAME)).toLowerCase().equals(series.getName().toLowerCase())) {
                        flag = true;
                        break;
                    }
                } while (cursor.moveToNext());
            }
        }
        if (!flag) {
            mkNewTable(series, db);
        }
        db.insert(CryptUtils.encode(series.getName()), null, mkValues(series));
        return flag;
    }
}
