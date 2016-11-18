package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Kristen on 11/16/2016.
 */
public class NumberInputActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    EditText date, weight, hr, bp;
    String strDate, strWeight, strHR, strBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberinput);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();
        date = (EditText)findViewById(R.id.etInputDate);
        weight = (EditText)findViewById(R.id.etInputWeight);
        hr = (EditText)findViewById(R.id.etInputHR);
        bp = (EditText)findViewById(R.id.etInputBP);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("myNumbers") // TODO: Define a title for the content shown.
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

    public void writeNumbers(View view) throws IOException {
        strDate = date.getText().toString() + "\n";
        strWeight = weight.getText().toString() + "\n";
        strHR = hr.getText().toString() + "\n";
        strBP = bp.getText().toString() + "\n";
        FileOutputStream fout = openFileOutput("myNumbers.txt", MODE_APPEND);

        fout.write(strDate.getBytes());
        fout.write(strWeight.getBytes());
        fout.write(strHR.getBytes());
        fout.write(strBP.getBytes());
        fout.close();
        Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
    }




}

