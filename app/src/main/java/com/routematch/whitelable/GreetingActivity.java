package com.routematch.whitelable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_greeting);

        TextView textView = (TextView) findViewById(R.id.text_greeting_info);

        TextView hello = (TextView) findViewById(R.id.text_greeting_message);

        Button greeting = (Button) findViewById(R.id.button_greeting_action);
        greeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (BuildConfig.PAID_VERSION) {// this is the flag configured in build.gradle
            textView.setText(getString(R.string.msg_paid));
        } else {
            textView.setText(getString(R.string.msg_free));
        }
    }
}
