package com.derich.bizzwiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class BizwizOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bizwiz.db";
    public static final Integer DATABASE_VERSION = 1;

    public BizwizOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BizwizDatabaseContract.users.SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
