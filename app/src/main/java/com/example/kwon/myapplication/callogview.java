package com.example.kwon.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleCursorAdapter;


public class callogview extends Activity {
    PopupWindow popup;
    View popupview;
    ListView listview;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calllogview);

        m_cPhoneCursor = getCursor();
        ListAdapter sPhoneAdapter = getAdapter();

        ListView contactsListView = (ListView)findViewById(R.id.loglist);
        contactsListView.setAdapter(sPhoneAdapter);

    }
    private ListAdapter getAdapter() {
        int iLayoutID = android.R.layout.two_line_list_item;
        int[] iTextViewID = new int[] {
                android.R.id.text1, android.R.id.text2
        };
        return new SimpleCursorAdapter(this, iLayoutID, m_cPhoneCursor, CallPROJECTION, iTextViewID);
    }
}