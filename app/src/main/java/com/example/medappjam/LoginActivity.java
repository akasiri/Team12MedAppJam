package com.example.medappjam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }

    public void submitSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

        // TODO should we be calling finish()?
        super.finish();
        finish();
    }


}