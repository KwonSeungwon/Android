package com.example.kwon.myapplication;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Ddayadd extends Activity {
    private static final String TABLE = "basic_info";


    private TextView ddayText;
    private TextView todayText;
    private TextView resultText;
    private Button dateButton;
    Button btnSave = null;
    Button btnCancel = null;

    private int tYear;         //오늘 연월일 변수
    private int tMonth;
    private int tDay;

    private int dYear = 1;        //디데이 연월일 변수
    private int dMonth = 1;
    private int dDay = 1;


    private long d;
    private long t;
    private long r;

    private int resultNumber = 0;

    static final int DATE_DIALOG_ID = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddayadd);

        ddayText = (TextView) findViewById(R.id.dday);
        todayText = (TextView) findViewById(R.id.today);
        resultText = (TextView) findViewById(R.id.result);
        dateButton = (Button) findViewById(R.id.dateButton);

        btnSave = (Button) findViewById(R.id.savebtn);
        btnCancel = (Button) findViewById(R.id.canclebtn);

        dateButton.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(0);//----------------
            }
        });


        Calendar calendar = Calendar.getInstance();              //현재 날짜 불러옴
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONTH);
        tDay = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar dCalendar = Calendar.getInstance();
        dCalendar.set(dYear, dMonth, dDay);

        t = calendar.getTimeInMillis();                 //오늘 날짜를 밀리타임으로 바꿈
        d = dCalendar.getTimeInMillis();              //디데이날짜를 밀리타임으로 바꿈
        r = (d - t) / (24 * 60 * 60 * 1000);                 //디데이 날짜에서 오늘 날짜를 뺀 값을 '일'단위로 바꿈

        resultNumber = (int) r + 1;
        updateDisplay();

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=null;
                if(db==null){
                    db=openOrCreateDatabase("sqlite_basic.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                }
                String result = resultText.getText().toString();

            }
        });

    }//OnCreate end


    private void updateDisplay() {

        todayText.setText(String.format("%d년 %d월 %d일", tYear, tMonth + 1, tDay));
        ddayText.setText(String.format("%d년 %d월 %d일", dYear, dMonth + 1, dDay));

        if (resultNumber >= 0) {
            resultText.setText(String.format("D-%d", resultNumber));
        } else {
            int absR = Math.abs(resultNumber);
            resultText.setText(String.format("디데이가 %d일 지났습니다", absR));
        }
    }//디데이 날짜가 오늘날짜보다 뒤에오면 '-', 앞에오면 '+'를 붙인다

    private DatePickerDialog.OnDateSetListener dDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            dYear = year;
            dMonth = monthOfYear;
            dDay = dayOfMonth;
            final Calendar dCalendar = Calendar.getInstance();
            dCalendar.set(dYear, dMonth, dDay);

            d = dCalendar.getTimeInMillis();
            r = (d - t) / (24 * 60 * 60 * 1000);

            resultNumber = (int) r;
            updateDisplay();
        }
    };



    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_DIALOG_ID) {
            return new DatePickerDialog(this, dDateSetListener, tYear, tMonth, tDay);

        }
        return null;


    }
}



