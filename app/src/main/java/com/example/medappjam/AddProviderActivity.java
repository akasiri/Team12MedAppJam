package com.example.medappjam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddProviderActivity extends AppCompatActivity {
    private Button mAddProviderSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        mAddProviderSubmit = (Button) findViewById(R.id.submit_add_provider);
        mAddProviderSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save provider information
            }
        });
    }
}
