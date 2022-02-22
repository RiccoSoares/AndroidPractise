package com.example.q2;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {
    TextView outputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        outputMessage = (TextView) findViewById(R.id.output);
        outputMessage.setText(message);
    }
}