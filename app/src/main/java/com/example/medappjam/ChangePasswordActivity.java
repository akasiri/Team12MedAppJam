package com.example.medappjam;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mCancel;
    private EditText mCurrentPassword;
    private EditText mNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mCancel = (Button) findViewById(R.id.password_change_cancel);
        mCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mCurrentPassword = (EditText) findViewById(R.id.current_password);
        mNewPassword = (EditText) findViewById(R.id.new_password);

        mSubmit = (Button) findViewById(R.id.submit_password_change);
        mSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
                String username = sharedPref.getString(getString(R.string.user), "");

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                Patient patient = db.getPatient(username);

                if(mCurrentPassword.getText().toString().equals(patient.getPassword())) {
                    patient.setPassword(mNewPassword.getText().toString());
                    db.updatePatient(patient);

                    Context context = getApplicationContext();
                    CharSequence text = "Updated password!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();

                    finish();
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect password. Please try again.";
                    int duration = Toast.LENGTH_LONG;

                    Toast.makeText(context, text, duration).show();
                }
            }
        });
    }
}
