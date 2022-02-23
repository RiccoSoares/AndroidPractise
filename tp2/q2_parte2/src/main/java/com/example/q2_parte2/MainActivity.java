package com.example.q2_parte2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView lightOutput;
    TextView ambientTempOutput;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private Sensor lightSensor;
    private Sensor ambientTempSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightOutput = (TextView) findViewById(R.id.lightInfo);
        ambientTempOutput = (TextView) findViewById(R.id.ambientTempInfo);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        ambientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if(lightSensor == null){
            lightOutput.setText("No Light Sensor Found In Your Device");
        }
        if(ambientTempSensor == null){
            ambientTempOutput.setText("No Ambient Temperature Sensor Found In Your Device");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        switch(sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                float lumValue = sensorEvent.values[0];
                lightOutput.setText("Luminosity: " + lumValue);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                float ambientTempValue = sensorEvent.values[0];
                ambientTempOutput.setText("Ambient Temperature: " + ambientTempValue);
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, ambientTempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }
}