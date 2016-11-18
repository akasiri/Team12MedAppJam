package com.example.medappjam;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class RecordsTableActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView  weight, hr, bp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordstable);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();


        String data;
        FileInputStream fin = null;
        try {
            fin = openFileInput("myNumbers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(insr);
        StringBuffer strbuff = new StringBuffer();
        try {
            while((data = bufferedReader.readLine())!=null){
                strbuff.append(data +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

   //     weight.setText(strbuff.toString());
 //       weight.setVisibility(View.VISIBLE);




        TableLayout t1;

        TableLayout tl = (TableLayout) findViewById(R.id.mainTable);


        TableRow tr_head = new TableRow(this);
        //tr_head.setId(10);
        tr_head.setBackgroundColor(Color.parseColor("#5c8e79"));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));

        TextView label_date = new TextView(this);
        label_date.setWidth(250);
        label_date.setTextSize(15);
        //label_date.setId(20);
        label_date.setText("DATE");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(10, 10, 10, 10);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight = new TextView(this);
        label_weight.setWidth(250);
        label_weight.setTextSize(15);
       // label_weight.setId(22);// define id that must be unique
        label_weight.setText("Weight"); // set the text for the header
        label_weight.setTextColor(Color.WHITE); // set the color
        label_weight.setPadding(10, 10, 10, 10); // set the padding (if required)
        tr_head.addView(label_weight); // add the column to the table row here


        TextView label_bp = new TextView(this);
        label_bp.setWidth(250);
        label_bp.setTextSize(15);
      //  label_bp.setId(23);// define id that must be unique
        label_bp.setText("BP"); // set the text for the header
        label_bp.setTextColor(Color.WHITE); // set the color
        label_bp.setPadding(10, 10, 10, 10); // set the padding (if required)
        tr_head.addView(label_bp); // add the column to the table ro


        TextView label_hr = new TextView(this);
        label_hr.setWidth(250);
        label_hr.setTextSize(15);
     //   label_hr.setId(24);// define id that must be unique
        label_hr.setText("HR"); // set the text for the header
        label_hr.setTextColor(Color.WHITE); // set the color
        label_hr.setPadding(10, 10, 10, 10); // set the padding (if required)
        tr_head.addView(label_hr); // add the column to the table ro


        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));



        Integer count=1;
        while (count < 31) {
            String date = count + ".";
            String weight = count+ "5";// get the second variable
            String hr = "###";
            String bp = "##/##";
// Create the table row
            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(Color.parseColor("#74b298"));
            else tr.setBackgroundColor(Color.parseColor("#9fceba"));
            tr.setId(100+count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

//Create four columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(this);
            labelDATE.setId(200+count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);

            TextView labelWEIGHT = new TextView(this);
            labelWEIGHT.setId(200+count);
            labelWEIGHT.setText(weight.toString());
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

            TextView labelBP = new TextView(this);
            labelBP.setId(200+count);
            labelBP.setText(bp);
            labelBP.setPadding(2, 0, 5, 0);
            labelBP.setTextColor(Color.WHITE);
            tr.addView(labelBP);

            TextView labelHR = new TextView(this);
            labelHR.setId(200+count);
            labelHR.setText(hr.toString());
            labelHR.setTextColor(Color.WHITE);
            tr.addView(labelHR);


// finally add this to the table row
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
        }





    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Month's Records") // TODO: Define a title for the content shown.
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

