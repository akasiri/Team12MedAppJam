package com.example.medappjam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class HomeActivity extends AppCompatActivity {
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


//        Button fab = (Button) findViewById(R.id.btnRecordsTable);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                launchRecordsTable(view);
//
//            }
//
//
//        });
//
//        Button btnNumbersInput = (Button) findViewById(R.id.btnNumbersInput);
//        btnNumbersInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                launchNumbersInput(view);
//            }
//
//
//        });

    public void switchToArzangTest(View view) {
        Intent intent = new Intent(this, ArzangTest.class);
        startActivity(intent);
    }

    public void launchRecordsTable(View view) {
        Intent intent = new Intent(this, RecordsTableActivity.class);
        startActivity(intent);
    }

    public void launchNumbersInput(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        startActivity(intent);
    }

    public void settingsScreen(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void myProvidersScreen(View view) {
        Intent intent = new Intent(this, MyProvidersActivity.class);
        startActivity(intent);
    }
}
