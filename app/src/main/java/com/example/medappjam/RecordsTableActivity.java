package com.example.medappjam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class RecordsTableActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView weight, hr, bp;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordstable);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();
        try {
            readToTable();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void readToTable() throws IOException {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.user), "");
        String data;
        FileInputStream fin = null;
        if (!user.isEmpty()) {
            filename = user + ".txt";
        } else {
            filename = "myNumbers.txt";
        }
        try {
            fin = openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(insr);
        ArrayList<String[]> lines = new ArrayList<>();
        int outCount = 0;
        try {
            while ((data = bufferedReader.readLine()) != null) {
                String linetemp[] = data.split(",");
                lines.add(outCount, linetemp);
                outCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = showAverages(lines);
        TableLayout tl = (TableLayout) findViewById(R.id.mainTable);
        addTableHeader(tl);
        addTableBody(tl, lines);
    }

    public int showAverages(ArrayList<String[]> lines) {
        TextView AveWeight = (TextView) findViewById(R.id.tvAveWeight);
        TextView AveHR = (TextView) findViewById(R.id.tvAveHR);
        TextView AveBP = (TextView) findViewById(R.id.tvAveBP);
        int wlength = 0;
        int hlength = 0;
        int slength = 0;
        int dlength = 0;
        int i = 0;
        float weightCount = 0;
        int systolicCount = 0;
        int diastolicCount = 0;
        int hrCount = 0;
        while (i < lines.size() && i < 30) {
            if (lines.get(i) != null) {
                String[] temp = lines.get(lines.size() - (i + 1));
                if (!temp[1].isEmpty() && !temp[1].contains("-")) {
                    weightCount += Float.parseFloat(temp[1]);
                    wlength++;
                }
                if (!temp[2].isEmpty()) {
                    hrCount += Integer.parseInt(temp[2].split(" ")[0]);
                    hlength++;
                }
                int index = temp[3].indexOf("/");
                if (temp[3].length() > 2) {
                    if (!temp[3].substring(0, index).isEmpty()) {
                        systolicCount += Integer.parseInt(temp[3].substring(0, index));
                        slength++;
                    }
                    if (!temp[3].substring(index + 1).isEmpty()) {
                        diastolicCount += Integer.parseInt(temp[3].substring(index + 1));
                        dlength++;
                    }
                }
            }
            i++;
        }
        if (wlength != 0) {
            AveWeight.setText(String.format("%5.1f", weightCount / wlength));
        }
        if (hlength != 0) {
            AveHR.setText(String.format("%d", hrCount / hlength));
        }
        if (dlength != 0 && slength != 0) {
            AveBP.setText(String.format("%d/%d", (systolicCount / slength), (diastolicCount / dlength)));
        }

        return wlength;
    }

    public void backClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void addTableHeader(TableLayout tl) {
        tl.setBackgroundColor(Color.WHITE);

        TableRow tr_head = new TableRow(this);
        //tr_head.setId(10);
        tr_head.setBackgroundColor(Color.parseColor("#5c8e79"));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));

        TextView label_date = new TextView(this);
        int headerTextSize = 18;
        label_date.setTextSize(15);
        //label_date.setId(20);
        label_date.setText("Date");
        label_date.setTextColor(Color.WHITE);
        label_date.setTextSize(headerTextSize);
        label_date.setPadding(10, 10, 10, 10);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight = new TextView(this);

        label_weight.setTextSize(15);
        // label_weight.setId(22);// define id that must be unique
        label_weight.setText("Weight"); // set the text for the header
        label_weight.setTextColor(Color.WHITE); // set the color
        label_weight.setPadding(10, 10, 10, 10); // set the padding (if required)
        label_weight.setTextSize(headerTextSize);
        tr_head.addView(label_weight); // add the column to the table row here


        TextView label_hr = new TextView(this);

        label_hr.setTextSize(15);
        //  label_bp.setId(23);// define id that must be unique
        label_hr.setText("HR"); // set the text for the header
        label_hr.setTextColor(Color.WHITE); // set the color
        label_hr.setPadding(10, 10, 10, 10); // set the padding (if required)
        label_hr.setTextSize(headerTextSize);
        tr_head.addView(label_hr); // add the column to the table ro


        TextView label_bp = new TextView(this);

        label_bp.setTextSize(15);
        //   label_hr.setId(24);// define id that must be unique
        label_bp.setText("BP"); // set the text for the header
        label_bp.setTextColor(Color.WHITE); // set the color
        label_bp.setPadding(10, 10, 10, 10); // set the padding (if required)
        label_bp.setTextSize(headerTextSize);
        tr_head.addView(label_bp); // add the column to the table ro


        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));
    }

    public void addTableBody(TableLayout tl, ArrayList<String[]> lines) {
        String weight, date, hr, bp;
        int count = 0;
        int length = lines.size();
        while (count < length) {
            if (lines.size() > count) {
                String[] temp2 = lines.get(length - (count + 1));
                if (!temp2[0].isEmpty()) {
                    date = temp2[0];
                } else {
                    date = "";
                }
                if (!temp2[1].isEmpty()) {
                    weight = temp2[1];
                    System.out.println(weight);
                } else {
                    weight = "";
                }
                if (!temp2[2].isEmpty()) {
                    bp = temp2[2];
                } else {
                    bp = "";
                }
                if (!temp2[2].isEmpty()) {
                    hr = temp2[3];
                } else {
                    hr = "";
                }

// Create the table row
                TableRow tr = new TableRow(this);
                if (count % 2 != 0)
                    tr.setBackgroundColor(Color.parseColor("#74b298"));
                else
                    tr.setBackgroundColor(Color.parseColor("#9fceba"));
                tr.setId(100 + count);
                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                int dataTextSize = 15;
//Create four columns to add as table data
                // Create a TextView to add date
                TextView labelDATE = new TextView(this);
                labelDATE.setId(200 + count);
                labelDATE.setText(date);
                labelDATE.setPadding(2, 0, 5, 0);
                labelDATE.setTextColor(Color.WHITE);
                tr.addView(labelDATE);

                TextView labelWEIGHT = new TextView(this);
                labelWEIGHT.setId(200 + count);
                labelWEIGHT.setText(weight);
                labelWEIGHT.setTextColor(Color.WHITE);
                tr.addView(labelWEIGHT);

                TextView labelBP = new TextView(this);
                labelBP.setId(200 + count);
                labelBP.setText(bp);
                labelBP.setPadding(2, 0, 5, 0);
                labelBP.setTextColor(Color.WHITE);
                tr.addView(labelBP);

                TextView labelHR = new TextView(this);
                labelHR.setId(200 + count);
                labelHR.setText(hr);
                labelHR.setTextColor(Color.WHITE);
                tr.addView(labelHR);


// finally add this to the table row
                tl.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                count++;
            }
        }
    }

    public void launchNumbersInput(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        startActivity(intent);
    }

//    public void editToday() throws IOException {
//        String user;
//        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
//        user = sharedPref.getString(getString(R.string.user), "");
//        String data;
//        filename = user + ".txt";
//        FileOutputStream fout = openFileOutput(filename, MODE_APPEND);
//        FileInputStream fin = null;
//        try {
//            fin = openFileInput(filename);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        fin.close();
//        InputStreamReader insr = new InputStreamReader(fin);
//        BufferedReader bufferedReader = new BufferedReader(insr);
//        ArrayList<String> lines = new ArrayList<>();
//        int outCount = 0;
//        try{
//            while ((data = bufferedReader.readLine()) != null) {
//                lines.add(outCount, data);
//                outCount++;
//            }}catch (IOException e) {
//            e.printStackTrace();
//        }
//        int k=0;
//        while(k < lines.size()-1) {
//            fout.write(lines.get(k).getBytes());
//            fout.close();
//            k++;
//        }
//    }

}
