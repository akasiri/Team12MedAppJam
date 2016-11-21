package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submitLogin(View view) {
        EditText username = (EditText) findViewById(R.id.povider_name_input);
        EditText password = (EditText) findViewById(R.id.provider_phonenumber_input);

        // TODO compare username and password to database for a match
        // if (match) {
        String user = username.getText().toString();
        String pw = password.getText().toString();

        DatabaseHandler db = new DatabaseHandler(this);
        Patient patient = db.getPatient(user);
        if(patient != null) {
            if(patient.getUsername().equals(user) && patient.getPassword().equals(pw)) {
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString(getString(R.string.user), user);
                edit.putBoolean(getString(R.string.isLoggedIn), true);
                edit.commit();

                Log.d("login", "logged in!");

                //return to homepage
                finish();
            }
            else {
                //show error message: Password is wrong
                Log.d("login", "incorrect password");
                findViewById(R.id.login_warning_image).setVisibility(View.VISIBLE);
                findViewById(R.id.login_warning_message).setVisibility(View.VISIBLE);
            }
        }
        else {
            //show error message: No account associated with username
            Log.d("login", "no account associated with username");
            findViewById(R.id.login_warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.login_warning_message).setVisibility(View.VISIBLE);
        }

        /**
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);


        // TODO should we be calling finish()?
        super.finish();
        finish();
        // }
        // else {
        findViewById(R.id.login_warning_image).setVisibility(View.VISIBLE);
        findViewById(R.id.login_warning_message).setVisibility(View.VISIBLE);
        // }
         */
    }

    public void submitSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

        // TODO should we be calling finish()?
        super.finish();
        finish();
    }


}