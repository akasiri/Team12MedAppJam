package com.example.medappjam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class DeleteRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            deleteRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteRecords() throws IOException {
        String filename;
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.user), "");
        filename = user +".txt";
        FileOutputStream fout = openFileOutput(filename, MODE_PRIVATE);
        fout.write("".getBytes());
        fout.close();
        Toast.makeText(getBaseContext(), "Data Deleted", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void deleteClick(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
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


