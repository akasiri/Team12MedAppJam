package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    public void onResume() {
        super.onResume();

        // TODO if this is the first time resuming (includes loading up?) this activity today, switch to the "how do you feel?" activity
        // need to keep a file storing the last time the activity was created?

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        if(sharedPref.getBoolean(getString(R.string.isLoggedIn), false)) {
            Button loginButton = (Button) findViewById(R.id.button1);
            loginButton.setVisibility(View.GONE);

            Button logoutButton = (Button) findViewById(R.id.button5);
            logoutButton.setVisibility(View.VISIBLE);
        }
        else {
            Button loginButton = (Button) findViewById(R.id.button1);
            loginButton.setVisibility(View.VISIBLE);

            Button settingsButton = (Button) findViewById(R.id.button4);
            settingsButton.setVisibility(View.GONE);

            Button logoutButton = (Button) findViewById(R.id.button5);
            logoutButton.setVisibility(View.GONE);
        }
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

    public void launchLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
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

    public void logout(View view) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        sharedPref.edit().putBoolean(getString(R.string.isLoggedIn), false).commit();

        onResume();
        /**
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
         */
    }
}
