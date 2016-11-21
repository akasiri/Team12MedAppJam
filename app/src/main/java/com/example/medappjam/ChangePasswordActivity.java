package com.example.medappjam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mCancel;

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
    }
}
