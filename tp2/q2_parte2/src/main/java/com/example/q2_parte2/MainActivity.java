package com.example.q2_parte2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView output;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private Sensor lightSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.lightInfo);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(lightSensor == null){
            output.setText("No Light Sensor Found In Your Device");
            finish();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        float lumValue = sensorEvent.values[0];
        output.setText("Luminosity: " + lumValue);

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }
}