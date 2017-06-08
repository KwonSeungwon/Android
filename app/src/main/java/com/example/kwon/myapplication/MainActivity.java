package com.example.kwon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button test = (Button) findViewById(R.id.clabtn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), cmain.class);
                startActivity(Intent);
            }
        });

        Button test2 = (Button) findViewById(R.id.ddaybtn);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), main.class);
                startActivity(Intent);
            }
        });
        Button test3 = (Button) findViewById(R.id.phbtn);
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), Phmain.class);
                startActivity(Intent);

            }
        });

    }
}























