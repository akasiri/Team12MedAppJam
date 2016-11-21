package com.example.medappjam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kristen on 11/16/2016.
 */
public class NumberInputActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    EditText day, month, year, weight, hr, systolic, diastolic;
    String strDate, strWeight, strHR, strBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberinput);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String currentYear = currentDateTimeString.substring(10,12);
        String currentDay = currentDateTimeString.substring(4,6);
        String currentMonth = "";
        switch(currentDateTimeString.substring(0,3)){
            case "Jan": currentMonth = "1";
                break;
            case "Feb": currentMonth = "2";
                break;
            case "Mar": currentMonth = "3";
                break;
            case "Apr": currentMonth = "4";
                break;
            case "May": currentMonth = "5";
                break;
            case "Jun": currentMonth = "6";
                break;
            case "Jul": currentMonth = "7";
                break;
            case "Aug": currentMonth = "8";
                break;
            case "Sep": currentMonth = "9";
                break;
            case "Oct": currentMonth = "10";
                break;
            case "Nov": currentMonth = "11";
                break;
            case "Dec": currentMonth = "12";
                break;
        }


        day = (EditText)findViewById(R.id.etInputDay);
        day.setText(currentDay);
        month = (EditText)findViewById(R.id.etInputMonth);
        month.setText(currentMonth);
        year = (EditText)findViewById(R.id.etInputYear);
        year.setText(currentYear);
        weight = (EditText)findViewById(R.id.etInputWeight);
        hr = (EditText)findViewById(R.id.etInputHR);
        systolic = (EditText)findViewById(R.id.etInputSystolic);
        diastolic = (EditText)findViewById(R.id.etInputDiastolic);

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

        strDate = day.getText().toString() + "/" + month.getText().toString() + "/" + year.getText().toString() + "\n";
        strWeight = weight.getText().toString() + "\n";


        strHR = hr.getText().toString() + "\n";
        strBP = systolic.getText().toString() + "/" + diastolic.getText() + "\n";
        FileOutputStream fout = openFileOutput("myNumbers.txt", MODE_APPEND);

        fout.write(strDate.getBytes());
        fout.write(strWeight.getBytes());
        fout.write(strHR.getBytes());
        fout.write(strBP.getBytes());
        fout.close();
        Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();

        readNumbers();
    }

    public void readNumbers(){
        String data;
        FileInputStream fin = null;
        try {fin = openFileInput("myNumbers.txt");}
        catch(FileNotFoundException e) {e.printStackTrace();}
        try {fin = openFileInput("myNumbers.txt");}
        catch(FileNotFoundException e) {e.printStackTrace();}

        InputStreamReader insr = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(insr);
        StringBuffer strbuff = new StringBuffer();
        String[] temp = new String[4];
        ArrayList<String> lines = new ArrayList<>();


        int outCount = 0;
        int subCount = 0;
        StringBuffer dataStr = new StringBuffer();
        try {
            while ((data = bufferedReader.readLine()) != null) {
                if (subCount < 3) {
                    dataStr.append(data + ",");
                    subCount++;
                }
                else {
                    dataStr.append(data);
                    lines.add(outCount, dataStr.toString());
                    System.out.println(lines.get(outCount));
                    dataStr.delete(0, dataStr.length());
                    subCount = 0;
                    outCount++;
                }

            }
        }

        catch(IOException e)
        {e.printStackTrace();}
        int i;
        String[] temp2;
        String[][] strarr = new String[lines.size()][4];
        for (i = 0; i < lines.size(); i++) {
            temp2 = lines.get(i).split(",");
            strarr[i] = temp2;
        }
        int t = Integer.parseInt(strarr[lines.size() - 2][1]) + 3;
        System.out.println(Integer.parseInt(strarr[lines.size() - 1][1]) + " , " + (t));

        StringBuffer strbuffer = new StringBuffer();
        int cw = (Integer.parseInt(strarr[lines.size() - 1][1]));
        int wthreshday = Integer.parseInt(strarr[lines.size() - 2][1]) + 3;
        int chr = (Integer.parseInt(strarr[lines.size() - 1][2]));
        int j = 0;
        int lowWeight, highWeight;
        lowWeight = Integer.parseInt(strarr[lines.size() - 2][1]);
        highWeight = 0;
        int sys = Integer.parseInt(systolic.getText().toString());
        int dia = Integer.parseInt(diastolic.getText().toString());
        boolean alertFlag = false;
        if(lines.size() >= 2) {
            while (j < 6) {
                String[] weekWeight = new String[7];
                int w = Integer.parseInt(strarr[lines.size() - (3 + j)][1]);
                if (w > highWeight) {
                    highWeight = w;
                } else if (w < lowWeight) {
                    lowWeight = w;
                }
                j++;
            }
            if (cw >= wthreshday) {
                strbuffer.append("Weight gain is over 3 pounds since yesterday.\n");
                alertFlag = true;
            }
            if (cw > highWeight + 5) {
                strbuffer.append("Weight increased more than 5 pounds this week.\n");
                alertFlag = true;
            } else if (cw < lowWeight - 5) {
                strbuffer.append("Weight decreased more than 5 pounds this week.\n");
                alertFlag = true;
            }
        }
            if (chr > 100){
                strbuffer.append("Heart rate is too high.\n");
                alertFlag = true;
            }
            else if (chr < 50){
                strbuffer.append("Heart rate is too low.\n");
                alertFlag = true;
            }
            if (sys < 90 || dia < 50){
                strbuffer.append("Blood pressure is dangerously low.\n");
                alertFlag = true;
            }
            else if (sys > 160 || dia > 90){
                strbuffer.append("Blood pressure is dangerously high.\n");
                alertFlag = true;
            }
            else{
                back();
            }

        String message = strbuffer.toString();
        if(alertFlag){
        alertProvider(message);
    }


    }

    public void alertProvider(String message){
        new AlertDialog.Builder(this)
                .setTitle("WARNING! Please contact health care services.")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        back();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    public void back(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}

