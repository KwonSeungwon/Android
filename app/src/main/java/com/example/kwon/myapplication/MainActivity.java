package com.example.kwon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kwon.myapplication.PhoneBook.Phmain;
import com.example.kwon.myapplication.Scheduler.cmain;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button test = (Button) findViewById(R.id.clabtn);  //일정관리 메뉴로 이동
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), cmain.class);
                startActivity(Intent);
            }
        });

        Button test3 = (Button) findViewById(R.id.phbtn); //전화번호부 메뉴로 이동
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), Phmain.class);
                startActivity(Intent);

            }
        });

    }
}























