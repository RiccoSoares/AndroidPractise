package com.example.q3_parte2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.pm.PackageManager;
import android.Manifest;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
    TextView lightOutput;
    TextView ambientTempOutput;
    TextView latOutput;
    TextView longOutput;
    private SensorManager sensorManager;
    private LocationManager locationManager;
    private SensorEventListener sensorEventListener;
    private Sensor lightSensor;
    private Sensor ambientTempSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightOutput = (TextView) findViewById(R.id.lightInfo);
        ambientTempOutput = (TextView) findViewById(R.id.tempInfo);
        latOutput = (TextView) findViewById(R.id.latInfo);
        longOutput = (TextView) findViewById(R.id.longInfo);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        ambientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, 0);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            onLocationChanged(location);
        }
        if(lightSensor == null){
            lightOutput.setText("No Light Sensor Found In Your Device");
        }
        if(ambientTempSensor == null){
            ambientTempOutput.setText("No Ambient Temperature Sensor Found In Your Device");
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latOutput.setText("Latitude: " + location.getLatitude());
        longOutput.setText("Longitude: " + location.getLongitude());
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