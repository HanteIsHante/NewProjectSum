package com.example.hante.newprojectsum.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATEBASE_NAME = "New.db";
    public static final int DATEBASE_VERSION = 1;

    //TYPE:
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String LONG_TYPE = " LONG";
    private static final String COMMA_SEP = ",";

    private static final String USER_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBEntry.UserTable.TABLE_NAME +
                    " (" + DBEntry.UserTable._ID + NUMBER_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    DBEntry.UserTable.USER_NAME + TEXT_TYPE + COMMA_SEP +
                    DBEntry.UserTable.USER_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    DBEntry.UserTable.USER_EMAIL + TEXT_TYPE +
                    " )";
    private static final String REMIND_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBEntry.RemindTable.TABLE_NAME +
                    " (" + DBEntry.RemindTable._ID + NUMBER_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    DBEntry.RemindTable.REMIND_TITLE + TEXT_TYPE + COMMA_SEP +
                    DBEntry.RemindTable.REMIND_CONTENT + TEXT_TYPE + COMMA_SEP +
                    DBEntry.RemindTable.REMIND_DATA + LONG_TYPE +
                    " )";

    public DBHelper (Context context) {
        super(context, DATEBASE_NAME, null, DATEBASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USER_SQL);
        sqLiteDatabase.execSQL(REMIND_SQL);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
