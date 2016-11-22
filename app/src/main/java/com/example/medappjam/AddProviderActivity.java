package com.example.medappjam;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddProviderActivity extends AppCompatActivity {
    private Button mSubmit;
    private EditText mProviderName;
    private EditText mProviderPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        mProviderName = (EditText) findViewById(R.id.provider_name_input);
        mProviderPhone = (EditText) findViewById(R.id.provider_phone_number_input);

        mProviderPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mSubmit = (Button) findViewById(R.id.button_add_provider);
        mSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(AddProviderActivity.this);

                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
                String username = sharedPref.getString(getString(R.string.user),"");
                Log.d("myProviders", "username is: " + username);

                Provider provider = new Provider(mProviderName.getText().toString(), mProviderPhone.getText().toString());
                db.addProvider(provider, username);
                Log.d("addProvider", "added provider");
                //finish after adding provider
                finish();

                /**
                Patient patient = db.getPatient(username);

                ArrayList<Provider> providers = db.getAllProviders(patient.getPatientId());
                for(int i=0; i < providers.size(); i++) {
                    Log.d("patient", providers.get(i).getProviderId() + " " + providers.get(i).getName() + " " +  providers.get(i).getPhoneNumber());
                }
                 */
            }
        });
    }
}
