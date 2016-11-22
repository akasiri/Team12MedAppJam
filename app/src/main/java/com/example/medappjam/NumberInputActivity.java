package com.example.medappjam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.io.RandomAccessFile;
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
    EditText  weight, hr, systolic, diastolic;
    String strDate, strWeight, strHR, strBP;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberinput);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String currentDate = currentDateTimeString.substring(0,12);
        TextView date = (TextView)findViewById(R.id.tvInputDate);
        date.setText(currentDate);
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

    public void writeNumbers() throws IOException {
        String user;
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        user = sharedPref.getString(getString(R.string.user),"");

        filename = user + ".txt";
        FileOutputStream fout = openFileOutput(filename, MODE_APPEND);

        TextView d = (TextView)findViewById(R.id.tvInputDate);
        String  date = d.getText().toString().split(",")[0] + ",";
        strWeight = weight.getText().toString() + ",";
        strHR = hr.getText().toString() + ",";
        strBP = systolic.getText().toString() + "/" + diastolic.getText() + "\n";
            filename = user + ".txt";
        fout.write(date.getBytes());
        fout.write(strWeight.getBytes());
        fout.write(strHR.getBytes());
        fout.write(strBP.getBytes());
        fout.close();
        Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();

    }

    public void readNumbers() {
        SharedPreferences sp = getSharedPreferences("sessionInfo", Context.MODE_PRIVATE);
        String user = sp.getString("user", "myNumbers");
        System.out.println(user);
        filename = user +".txt";
        String data;
        FileInputStream fin = null;
        try {
            fin = openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader insr = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(insr);
        StringBuffer strbuff = new StringBuffer();
        String[] temp = new String[4];
        ArrayList<String[]> lines = new ArrayList<>();

        int outCount = 0;
        StringBuffer dataStr = new StringBuffer();
        try {
            while ((data = bufferedReader.readLine()) != null) {
                String linetemp[] = data.split(",");
                lines.add(outCount, linetemp);
                outCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!checkForAlert(lines).isEmpty()) {
            alertProvider(checkForAlert(lines));
        }
        else{
            back();
        }
    }

    public String checkWeightAlert(ArrayList<String[]> lines){
        float cw;
        StringBuffer strbuffer = new StringBuffer();
        if(lines.size() > 0) {
            if (!weight.getText().toString().isEmpty() && !weight.getText().toString().equals("0-806")) {
                cw = (Float.parseFloat(weight.getText().toString()));
                if (lines.size() >= 2) {
                    float wthreshday;
                    if (!lines.get(lines.size() - 2)[1].isEmpty() && !lines.get(lines.size() - 2)[1].equals("0-806")) {
                        wthreshday = Float.parseFloat(lines.get(lines.size() - 2)[1]);
                    } else {
                        wthreshday = cw;
                    }

                    int j = 0;
                    float w = 0;
                    float lowWeight, highWeight;
                    highWeight = 0;
                    if (!lines.get(lines.size() - 2)[1].isEmpty() && !lines.get(lines.size() - 2)[1].equals("0-806")) {
                        lowWeight = Float.parseFloat(lines.get(lines.size() - 2)[1]);
                    } else {
                        lowWeight = cw + 10;
                    }
                    while (j < 6) {
                        String[] weekWeight = new String[7];
                        if (lines.size() > 5) {
                            if (!lines.get(lines.size() - (3 + j))[1].isEmpty()) {
                                w = Float.parseFloat(lines.get(lines.size() - (3 + j))[1]);
                            }
                        }
                        if (w > highWeight) {
                            highWeight = w;
                        } else if (w < lowWeight) {
                            lowWeight = w;
                        }
                        j++;
                    }
                    if (cw >= wthreshday + 3) {
                        strbuffer.append("Weight gain is over 3 pounds since yesterday.\n");
                    }
                    if (cw > highWeight + 5) {
                        strbuffer.append("Weight increased more than 5 pounds this week.\n");
                    } else if (cw < lowWeight - 5) {
                        strbuffer.append("Weight decreased more than 5 pounds this week.\n");
                    }
                }
            }
        }
        return strbuffer.toString();
    }

    public String checkHR(ArrayList<String[]> lines){
        StringBuffer strbuffer = new StringBuffer();
        int chr;
        if(lines.size() > 0) {
            if (!hr.getText().toString().isEmpty()) {
                chr = (Integer.parseInt(hr.getText().toString()));
                if (chr > 100) {
                    strbuffer.append("Heart rate is too high.\n");
                } else if (chr < 50) {
                    strbuffer.append("Heart rate is too low.\n");
                }
            }
        }

        return strbuffer.toString();
    }

    public String checkForAlert(ArrayList<String[]> lines) {
        StringBuffer strbuffer = new StringBuffer();
        int dia, sys;
        String message = "";


        strbuffer.append(checkWeightAlert(lines));

        if (!diastolic.getText().toString().isEmpty() && !systolic.getText().toString().isEmpty()) {
            dia = Integer.parseInt(diastolic.getText().toString());
            sys = Integer.parseInt(systolic.getText().toString());
            if (sys < 90 || dia < 50) {
                strbuffer.append("Blood pressure is dangerously low.\n");
            } else if (sys > 160 || dia > 90) {
                strbuffer.append("Blood pressure is dangerously high.\n");
            }
        }
        strbuffer.append(checkHR(lines));
        message = strbuffer.toString();

        try {
            writeNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void alertProvider(String message){
        if(!message.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Please contact health services!")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            back();

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .create()
                    .show();
        }
        else{
        }

    }

    public void areNumbersGood(View view){
        EditText weightET = (EditText)findViewById(R.id.etInputWeight);
        String weight = weightET.getText().toString();
        EditText hrET = (EditText)findViewById(R.id.etInputHR);
        String hr = hrET.getText().toString();
        EditText sET = (EditText)findViewById(R.id.etInputSystolic);
        String systolic = sET.getText().toString();
        EditText dET = (EditText)findViewById(R.id.etInputDiastolic);
        String diastolic = dET.getText().toString();
        final String message = "Weight: "+  weight + "\nBlood Pressure: " + systolic + "/" + diastolic +"\nHeart Rate: " + hr;

        new AlertDialog.Builder(this)
                .setTitle("Is the input correct?")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        readNumbers();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.btn_dialog)
                .create()
                .show();
    }

    public void back(){
        Intent intent = new Intent(this, RecordsTableActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}

