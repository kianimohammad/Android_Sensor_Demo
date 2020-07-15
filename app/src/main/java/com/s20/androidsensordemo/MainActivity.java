package com.s20.androidsensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mAccelerometer, mGyro, mMagnet;

    TextView xAcl, yAcl, zAcl;
    TextView xGyro, yGyro, zGyro;
    TextView xMag, yMag, zMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMagnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        xAcl = findViewById(R.id.x_acl);
        yAcl = findViewById(R.id.y_acl);
        zAcl = findViewById(R.id.z_acl);

        xGyro = findViewById(R.id.x_gyro);
        yGyro = findViewById(R.id.y_gyro);
        zGyro = findViewById(R.id.z_gyro);

        xMag = findViewById(R.id.x_magnet);
        yMag = findViewById(R.id.y_magnet);
        zMag = findViewById(R.id.z_magnet);

        displayAcceleration();
        displayMagnetField();
        displayGyro();
    }

    private void displayGyro() {
        if (mGyro != null)
            sensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void displayMagnetField() {
        if (mMagnet != null)
            sensorManager.registerListener(this, mMagnet, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void displayAcceleration() {
        // check if the sensor is available
        if (mAccelerometer != null)
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL); // without delay
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                xAcl.setText(String.format("X: %s", sensorEvent.values[0]));
                yAcl.setText(String.format("Y: %s", sensorEvent.values[1]));
                zAcl.setText(String.format("Z: %s", sensorEvent.values[2]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                xMag.setText(String.format("X: %s", sensorEvent.values[0]));
                yMag.setText(String.format("Y: %s", sensorEvent.values[1]));
                zMag.setText(String.format("Z: %s", sensorEvent.values[2]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                xGyro.setText(String.format("X: %s", sensorEvent.values[0]));
                yGyro.setText(String.format("Y: %s", sensorEvent.values[1]));
                zGyro.setText(String.format("Z: %s", sensorEvent.values[2]));
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}