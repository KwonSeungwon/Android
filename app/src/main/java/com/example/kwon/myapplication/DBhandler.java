package com.example.kwon.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;


class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler open(Context ctx) {
        DBHandler hdlr = new DBHandler(ctx);
        return hdlr;
    }

    public void close() {
        helper.close();
    }

    public long insert(String datetime, String text) {
        ContentValues vals = new ContentValues();
        vals.put("datetime", datetime);
        vals.put("text", text);

        return db.insert("d_days", null, vals);
    }


    public Cursor selectAll() {
        Cursor cursor = db.rawQuery("SELECT _id, text||' '||datetime as msg FROM d_days", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public long delete(String id) {
        return db.delete("d_days", "_id=?", new String[] { id });
    }
}