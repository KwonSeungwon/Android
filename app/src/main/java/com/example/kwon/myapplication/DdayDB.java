package com.example.kwon.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DdayDB
        extends SQLiteOpenHelper
{
    private static final String DATABASE = "dday_db";

    public DdayDB(Context paramContext)
    {
        super(paramContext, "dday_db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE task_content (_id INTEGER PRIMARY KEY AUTOINCREMENT,content TEXT, level INTEGER, datetime TEXT, status INTEGER, dday TEXT);");
        paramSQLiteDatabase.execSQL("CREATE TABLE sys_set(_id INTEGER PRIMARY KEY AUTOINCREMENT, sort_type TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS task_content");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS sys_set");
        onCreate(paramSQLiteDatabase);
    }
}
