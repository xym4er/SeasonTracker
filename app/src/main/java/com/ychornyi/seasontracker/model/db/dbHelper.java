package com.ychornyi.seasontracker.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by y.chornyi on 15.06.2016.
 */
public class dbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Seasons";
    public static final int VERSION = 1;
    public static final String  = "";
    public static final String  = "";
    public static final String  = "";
    public static final String  = "";

    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
