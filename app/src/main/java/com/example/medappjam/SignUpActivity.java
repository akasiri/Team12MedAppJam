package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    PromptForProviderFragment promptProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        promptProvider = new PromptForProviderFragment();

    }

    public void submitSignUp(View view) {
        EditText username = (EditText) findViewById(R.id.username_input);
        EditText password = (EditText) findViewById(R.id.password_input);
        EditText passwordReenter = (EditText) findViewById(R.id.reentry_password_input);

        // if password is not blank and the user entered matching passwords...
        if (!password.getText().toString().equals(passwordReenter.getText().toString())) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.password_warning_message_not_equal);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else if (username.getText().toString().length() == 0) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.username_warning_message_empty);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else if (password.getText().toString().length() == 0) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.password_warning_message_empty);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else {
            // store username - password combination
            try {
                //File file = new File( username.getText().toString() + ".txt");
                //file.createNewFile();
                //PrintWriter writer = new PrintWriter(file);
                //writer.println(password.getText().toString());

                DatabaseHandler db = new DatabaseHandler(this);

                if(db.getPatient(username.getText().toString()) != null) {
                    Log.d("signUp", "username already exists " + username.getText().toString());
                    TextView warning = (TextView) findViewById(R.id.warning_message);
                    warning.setText(R.string.username_warning_already_exists);

                    findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
                    findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
                }
                else {
                    Log.d("tag", "added user to db");
                    Patient patient = new Patient(username.getText().toString(), password.getText().toString());
                    db.addPatient(patient);

                    SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPref.edit();
                    edit.putString(getString(R.string.user), username.getText().toString());
                    edit.putBoolean(getString(R.string.isLoggedIn), true);
                    edit.commit();

                    promptProvider.show(this.getFragmentManager(), "alert delete");
                    // the prompt will take you to the next activity
                }


                /**
                 * For debugging purposes, log all patients on file
                 */
                ArrayList<Patient> patients = db.getAllPatients();
                for(int i=0; i < patients.size(); i++) {
                    Log.d("patient", patients.get(i).getPatientId() + " " + patients.get(i).getUsername() + " " +  patients.get(i).getPassword());
                }

            }
            catch (Exception e) {
                System.err.println(e.getStackTrace());
            }
        }
    }
}
