package com.example.medappjam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProviderInfoActivity extends AppCompatActivity {
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_provider_info);

        mSubmit = (Button) findViewById(R.id.submit_provider_changes);
        mSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add provider
            }
        });
    }
}
