package com.example.kwon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;;



public class Dday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dday);

        Button test = (Button) findViewById(R.id.ddaybtn2);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), Ddayadd.class);
                startActivity(Intent);

            }
        });
    }
}


