package com.example.q3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometer;

    TextView xOutput;
    TextView yOutput;
    TextView zOutput;
    Float x = null;
    Float y = null;
    Float z = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xOutput = (EditText) findViewById(R.id.outpu1);
        yOutput = (EditText) findViewById(R.id.output2);
        zOutput = (EditText) findViewById(R.id.output3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume(){
        super.onResume();
        x = null;
        y = null;
        z = null;
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            x=event.values[0];
            y=event.values[1];
            z=event.values[2];
            xOutput.setText("X:"+ x);
            yOutput.setText("Y:"+ y);
            zOutput.setText("Z:"+ z);

            if((Math.abs(x - event.values[0]) > 20)
                    ||(Math.abs(y - event.values[0]) > 20)
                    ||(Math.abs(z - event.values[0]) > 20)){
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
            }
        }
    }
}