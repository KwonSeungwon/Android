package com.example.kwon.myapplication.PhoneBook;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.kwon.myapplication.R;
import com.example.kwon.myapplication.Scheduler.cmain;

import java.util.Calendar;
import java.util.Date;


public class Phmain extends Activity {


    private Cursor m_cPhoneCursor;
    final static private String[] CallPROJECTION = {CallLog.Calls.NUMBER, CallLog.Calls.DATE};


    private Cursor getCursor() {
        Uri CallUri = CallLog.Calls.CONTENT_URI;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
        }
        return getContentResolver().query(CallUri, null, null, null, CallPROJECTION[0]);
    }

    @Override
    protected void onDestroy() {
        m_cPhoneCursor.close();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phmain);

        Button buttonRunContacts = (Button) findViewById(R.id.phonebtn);
        buttonRunContacts.setOnClickListener(buttonRunContactsClickListener);


        m_cPhoneCursor = getCursor();
        ListAdapter sPhoneAdapter = getAdapter();

        ListView contactsListView = (ListView) findViewById(R.id.loglist);
        contactsListView.setAdapter(sPhoneAdapter);
    }

    private ListAdapter getAdapter() {
        int iLayoutID = android.R.layout.two_line_list_item;
        int[] iTextViewID = new int[] {
                android.R.id.text1, android.R.id.text2
        };
        return new SimpleCursorAdapter(this, iLayoutID, m_cPhoneCursor, CallPROJECTION, iTextViewID);
    }


    OnClickListener buttonRunContactsClickListener = new OnClickListener() {

        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Contacts.CONTENT_URI);
            startActivity(intent);
        }

    };

}

























