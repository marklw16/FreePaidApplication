package com.routematch.whitelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainBrandedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text_main_type);

        TextView hello = (TextView) findViewById(R.id.text_main_info);

        Button greeting = (Button) findViewById(R.id.button_main_action);
        greeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBrandedActivity.this, GreetingActivity.class);
                startActivity(intent);
            }
        });

        textView.setText(getString(R.string.msg_paid));
    }
}
