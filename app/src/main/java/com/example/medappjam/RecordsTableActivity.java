package com.example.medappjam;

import android.content.DialogInterface;
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
import java.util.List;


public class RecordsTableActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView weight, hr, bp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordstable);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = getIntent();
        readToTable();
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

    public void deleteRecords() throws IOException {

        FileOutputStream fout = openFileOutput("myNumbers.txt", MODE_PRIVATE);
        fout.write("".getBytes());
        fout.close();
        Toast.makeText(getBaseContext(), "Data Deleted", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void readToTable(){
    int count;
    String data;

    FileInputStream fin = null;
    try {fin = openFileInput("myNumbers.txt");}
    catch(FileNotFoundException e) {e.printStackTrace();}
        try {fin = openFileInput("myNumbers.txt");}
        catch(FileNotFoundException e) {e.printStackTrace();}

    InputStreamReader insr = new InputStreamReader(fin);
    BufferedReader bufferedReader = new BufferedReader(insr);
    StringBuffer strbuff = new StringBuffer();
    String[][] lines = new String[30][4];


    int outCount = 0;
    int subCount = 0;
    try {
        while ((data = bufferedReader.readLine()) != null) {
               if (subCount == 4) {
                    subCount = 0;
                    outCount++;
                }
                lines[outCount][subCount] = data;
                    System.out.println(lines[outCount][subCount]);


            lines[outCount][subCount] = data;
            System.out.println(lines[outCount]);
            subCount++;


        }
    }

    catch(IOException e)
    {e.printStackTrace();}

    int length = showAverages(lines);

    TableLayout t1;

    TableLayout tl = (TableLayout) findViewById(R.id.mainTable);
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
    label_date.setPadding(10,10,10,10);
    tr_head.addView(label_date);// add the column to the table row here

    TextView label_weight = new TextView(this);

    label_weight.setTextSize(15);
    // label_weight.setId(22);// define id that must be unique
    label_weight.setText("Weight"); // set the text for the header
    label_weight.setTextColor(Color.WHITE); // set the color
    label_weight.setPadding(10,10,10,10); // set the padding (if required)
        label_weight.setTextSize(headerTextSize);
    tr_head.addView(label_weight); // add the column to the table row here


    TextView label_hr = new TextView(this);

    label_hr.setTextSize(15);
    //  label_bp.setId(23);// define id that must be unique
    label_hr.setText("HR"); // set the text for the header
    label_hr.setTextColor(Color.WHITE); // set the color
    label_hr.setPadding(10,10,10,10); // set the padding (if required)
        label_hr.setTextSize(headerTextSize);
    tr_head.addView(label_hr); // add the column to the table ro


    TextView label_bp = new TextView(this);

    label_bp.setTextSize(15);
    //   label_hr.setId(24);// define id that must be unique
    label_bp.setText("BP"); // set the text for the header
    label_bp.setTextColor(Color.WHITE); // set the color
    label_bp.setPadding(10,10,10,10); // set the padding (if required)
    label_bp.setTextSize(headerTextSize);
    tr_head.addView(label_bp); // add the column to the table ro


    tl.addView(tr_head,new TableLayout.LayoutParams(
    TableLayout.LayoutParams.WRAP_CONTENT,
    TableLayout.LayoutParams.WRAP_CONTENT
    ));


    count=0;
    ArrayList<String> splitLine = new ArrayList<>();
    while(count<length)

    {
        if (lines.length >= count) {
            String date = lines[count][0];
            String weight = lines[count][1];
            String bp = lines[count][2];
            String hr = lines[count][3];


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
            labelDATE.setTextSize(dataTextSize);
            tr.addView(labelDATE);

            TextView labelWEIGHT = new TextView(this);
            labelWEIGHT.setId(200 + count);
            labelWEIGHT.setText(weight);
            labelWEIGHT.setTextColor(Color.WHITE);
            labelDATE.setTextSize(dataTextSize);
            tr.addView(labelWEIGHT);

            TextView labelBP = new TextView(this);
            labelBP.setId(200 + count);
            labelBP.setText(bp);
            labelBP.setPadding(2, 0, 5, 0);
            labelBP.setTextColor(Color.WHITE);
            labelDATE.setTextSize(dataTextSize);
            tr.addView(labelBP);

            TextView labelHR = new TextView(this);
            labelHR.setId(200 + count);
            labelHR.setText(hr);
            labelHR.setTextColor(Color.WHITE);
            labelDATE.setTextSize(dataTextSize);
            tr.addView(labelHR);


// finally add this to the table row
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
        }
    }


}


    public int showAverages(String[][] lines){
        TextView AveWeight = (TextView)findViewById(R.id.tvAveWeight);
        TextView AveHR = (TextView)findViewById(R.id.tvAveHR);
        TextView AveBP = (TextView)findViewById(R.id.tvAveBP);
        int length = 0;
        int i = 0;
        float weightCount = 0;
        int systolicCount = 0;
        int diastolicCount = 0;
        int hrCount = 0;
        while(i<lines.length){
            if (lines[i][1] != null) {
                weightCount += Float.parseFloat(lines[i][1]);
                hrCount += Integer.parseInt(lines[i][2]);
                int index = lines[i][3].indexOf("/");
                systolicCount += Integer.parseInt(lines[i][3].substring(0,index));
                diastolicCount += Integer.parseInt(lines[i][3].substring(index+1));
                length ++;
            }
            i++;
        }
        if(length != 0) {
            AveWeight.setText(String.format("%5.1f", weightCount / length));
            AveHR.setText(String.format("%d", hrCount / length));
            AveBP.setText(String.format("%d/%d", (systolicCount / length), (diastolicCount / length)));
        }
        return length;
    }
    public void backClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void deleteClick(View view){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        try {
                            deleteRecords();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
