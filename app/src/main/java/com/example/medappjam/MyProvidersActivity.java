package com.example.medappjam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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

        //Get list of providers
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.user), "");
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Provider> providers = db.getAllProviders(username);

        // auto generate providers
        if(providers.isEmpty()) {
            //no providers yet. add provider.
            Log.d("myProviders", "no provider yet");
        }
        else {
            LinearLayout container =(LinearLayout) findViewById(R.id.providers_container);
            for(int i = 0; i < providers.size(); i++) {
                TextView providerName = new TextView(this);
                // TODO get provider name from database
                providerName.setText(providers.get(i).getName());

                LinearLayout phonenumberContainer = new LinearLayout(this);
                phonenumberContainer.setOrientation(LinearLayout.HORIZONTAL);

                TextView phonenumPrompt = new TextView(this);
                phonenumPrompt.setText("Phone Number: ");
                TextView phonenumber = new TextView(this);
                // TODO get provider number from database
                phonenumber.setText(providers.get(i).getPhoneNumber());

                phonenumberContainer.addView(phonenumPrompt);
                phonenumberContainer.addView(phonenumber);

                TextView blank = new TextView(this);

                container.addView(providerName);
                container.addView(phonenumberContainer);
                container.addView(blank);
            }
        }
    }

    public void addProvider(View view) {
        Intent intent = new Intent(this, AddProviderActivity.class);
        startActivity(intent);
    }
}
