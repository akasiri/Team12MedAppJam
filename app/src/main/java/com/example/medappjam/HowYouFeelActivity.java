package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;


/**
 * Created by Alicia on 11/21/16.
 */

public class HowYouFeelActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howareyoufeeling);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

//        Button doneButton = (Button) findViewById(R.id.done_button);
//        doneButton.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("howFeeling", "clicked done");
//                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
//                String username = sharedPref.getString(getString(R.string.user), "");
//
//                Calendar calendar = Calendar.getInstance();
//                int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
//                int currentYear = calendar.get(Calendar.YEAR);
//
//                InputDate currentDate = new InputDate(currentDay, currentYear);
//
//                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//                if(db.getDate(username) == null) {
//                    db.addDate(username, currentDate);
//                }
//                else {
//                    db.updateDate(username, currentDate);
//                }
//
//                Log.d("how are you feeling", "added date to db");
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//            }
//        });

    }

//    public void onClick(View view) {
//        if ((CheckBox) view.isChecked())
//        {
//
//        }
//    }

    public void buttonDone(View view) {

        Toast.makeText(this, "In done", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putBoolean(getString(R.string.user) + "_did_feel", true);
        edit.commit();

        finish();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("HowYouFeel Page") // Define a title for the content shown.
                // Make sure this auto-generated URL is correct.
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
