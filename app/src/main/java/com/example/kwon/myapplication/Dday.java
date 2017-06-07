package com.example.kwon.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;;import static com.example.kwon.myapplication.R.layout.ddayadd;


public class Dday extends AppCompatActivity {
    ListView listView = null;
    SimpleCursorAdapter adapter = null;
    Button btnCreate = null;


    private static final String Q_CREATE_TABLE = "CREATE TABLE basic_info( " +
            "id INTEGER PRI_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "dday TEXT," + "dmonth TEXT," + "dyear TEXT," + ");";

    private final String Q_GET_LIST = "SELECT * FROM basic_info" + " ORDER BY id_DESC";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dday);
        getDbData();

        btnCreate = (Button) findViewById(R.id.ddaybtn2);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Dday.this, Ddayadd.class);
                startActivity(intent);
            }

        });
    }

    protected void onResume() {
        super.onResume();
        getDbData();
    }

    private void getDbData() {
        SQLiteDatabase db = null;
        if (db == null) {
            db = openOrCreateDatabase("sqlite_basic.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }


        Cursor c = db.rawQuery(Q_GET_LIST, null);
        listView = (ListView) findViewById(R.id.Ddaylist);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c,
                new String[]{"result"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listView.setAdapter(adapter);
        listView.setDivider(new ColorDrawable(Color.rgb(149, 145, 145)));
        listView.setDividerHeight(2);
    }

        private void checkTablesCreated(SQLiteDatabase db) {
        Cursor c=db.query("sqlite_master",new String[]{"count(*)"},"name=?",new String[]{"basic_info"},null,null,null);
        Integer cnt=0;
        c.moveToFirst();
        while (c.isAfterLast()==false){
            cnt=c.getInt(0);
            c.moveToNext();
        }
        c.close();
    if(cnt==0){
        db.execSQL(Q_CREATE_TABLE);
    }
    }
}






