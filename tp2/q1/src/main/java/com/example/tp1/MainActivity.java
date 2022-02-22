package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText firstVar;
    EditText secondVar;
    Button actionSum;
    TextView resultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstVar = (EditText) findViewById(R.id.var1);
        secondVar = (EditText) findViewById(R.id.var2);
        actionSum = (Button) findViewById(R.id.button);
        resultDisplay = (TextView) findViewById(R.id.result);

        actionSum.setOnClickListener(v -> sumAndDisplay());

    }

    private void sumAndDisplay() {
        int sum = 0;
        int num1 = Integer.parseInt(firstVar.getText().toString());
        int num2 = Integer.parseInt(secondVar.getText().toString());
        sum = num1 + num2;
        resultDisplay.setText(Integer.toString(sum));
    }
}