package com.example.medappjam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.PrintWriter;

public class SignUpActivity extends AppCompatActivity {

    PromptForProviderFragment promptProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        promptProvider = new PromptForProviderFragment();

    }

    public void submitSignUp(View view) {
        EditText username = (EditText) findViewById(R.id.username_input);
        EditText password = (EditText) findViewById(R.id.password_input);
        EditText passwordReenter = (EditText) findViewById(R.id.reentry_password_input);

        // if password is not blank and the user entered matching passwords...
        if (!password.getText().toString().equals(passwordReenter.getText().toString())) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.password_warning_message_not_equal);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else if (username.getText().toString().length() == 0) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.username_warning_message_empty);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else if (password.getText().toString().length() == 0) {
            TextView warning = (TextView) findViewById(R.id.warning_message);
            warning.setText(R.string.password_warning_message_empty);

            findViewById(R.id.warning_image).setVisibility(View.VISIBLE);
            findViewById(R.id.warning_message).setVisibility(View.VISIBLE);
            return;
        }
        else {
            // store username - password combination
            try {
                File file = new File( username.getText().toString() + ".txt");
                file.createNewFile();
                PrintWriter writer = new PrintWriter(file);
                writer.println(password.getText().toString());
            }
            catch (Exception e) {
                System.err.println(e.getStackTrace());
            }

            promptProvider.show(this.getFragmentManager(), "alert delete");
            // the prompt will take you to the next activity
        }
    }
}
