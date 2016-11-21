package com.example.medappjam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyProvidersActivity extends AppCompatActivity {
    private Button mAddProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_providers);

        // auto generate providers
        LinearLayout container =(LinearLayout) findViewById(R.id.providers_container);
        for(int i = 0; i < 5; i++) {
            TextView providerName = new TextView(this);
            // TODO get provider name from database
            providerName.setText("Provider "+i);

            LinearLayout phonenumberContainer = new LinearLayout(this);
            phonenumberContainer.setOrientation(LinearLayout.HORIZONTAL);

            TextView phonenumPrompt = new TextView(this);
            phonenumPrompt.setText("Phone Number: ");
            TextView phonenumber = new TextView(this);
            // TODO get provider number from database
            phonenumber.setText("");

            phonenumberContainer.addView(phonenumPrompt);
            phonenumberContainer.addView(phonenumber);

            TextView blank = new TextView(this);

            container.addView(providerName);
            container.addView(phonenumberContainer);
            container.addView(blank);

        }
    }

    public void addProvider(View view) {
        Intent intent = new Intent(this, AddProviderActivity.class);
        startActivity(intent);
    }
}
