package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

/*
 * SHUOLD NOT BE ABLE TO ENTER HOME ACTIVITY WITHOUT LOGGING IN FIRST
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
//    private GoogleApiClient client;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_logout:
//                startActivity(new Intent(this, LoginActivity.class));
                logout(new View(this));
                return true;

//            case R.id.action_about:
//                startActivity(new Intent(this, About.class));
//                return true;

            case R.id.action_setting:
//                startActivity(new Intent(this, SettingsActivity.class));
                settingsScreen(new View(this));
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.user), "");

        TextView greeting = (TextView) findViewById(R.id.home_greeting);
        greeting.setText(getString(R.string.home_activity_greeting) + " " + username);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);

        // if logged in...
        if(sharedPref.getBoolean(getString(R.string.isLoggedIn), false)) {
           String username = sharedPref.getString(getString(R.string.user), "");

            // TODO replace sharedPref right here with database
            int prevDate = sharedPref.getInt(getString(R.string.user) +"_day", -1);

            Calendar calendar = Calendar.getInstance();
            int currentDate = calendar.get(Calendar.DAY_OF_YEAR);

            // TODO assumes that the calendar will never move back. If a date becomes smaller that is only because it looped.
            // TODO causes bug where if they user opens the app a year later on the same day, it will not prompt for vitals. (Can be resolved by also keeping track of previous year)

            // if not the first time running today...
            if (currentDate != prevDate) {
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putBoolean(username + "_did_feel", false);
                edit.putBoolean(username + "_did_vitals", false);
                edit.putInt(username + "_day", currentDate);
                edit.commit();
            }
            else {
                // use while loops here to make sure you don't load vitals before feeling is done
                while (sharedPref.getBoolean(username + "_did_feel", false)) {

                    Button button = (Button) findViewById(R.id.button1);
                    button.setText("ugghhh");

                    Intent feelingIntent = new Intent(this, HowYouFeelActivity.class);
                    startActivity(feelingIntent);
                }
                while (sharedPref.getBoolean(username + "_did_vitals", false)) {
                    Intent feelingIntent = new Intent(this, NumberInputActivity.class);
                    startActivity(feelingIntent);
                }
            }
        }
        else {

            // user is not logged in, should redirect to login screen

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            // we shouldn't finish() here. We want to be able to return once loginng in or signing up is complete
//            finish();
        }
    }

    public void launchLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void launchRecordsTable(View view) {
        Intent intent = new Intent(this, RecordsTableActivity.class);
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
        sharedPref.edit().clear().commit();

        // for debug database purging
//        this.deleteDatabase("patientProviderInfo");

        onResume();
        /**
         SharedPreferences.Editor editor = sharedPref.edit();
         editor.clear();
         editor.commit();
         */
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


