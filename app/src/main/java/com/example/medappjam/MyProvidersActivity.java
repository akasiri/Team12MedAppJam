package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyProvidersActivity extends AppCompatActivity {
    private Button mAddProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_providers);

        mAddProvider = (Button) findViewById(R.id.add_provider);
        mAddProvider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProvidersActivity.this, AddProviderActivity.class);
                startActivity(intent);
            }
        });
    }
}
