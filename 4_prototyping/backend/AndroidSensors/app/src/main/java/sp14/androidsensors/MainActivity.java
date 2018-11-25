// https://stackoverflow.com/questions/33415033/getting-current-location-in-android-studio-app
// https://developer.android.com/guide/topics/sensors/sensors_overview

package sp14.androidsensors;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Build;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.util.Log;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.content.Context;

public class MainActivity extends Activity implements SensorEventListener, LocationListener {

    private Sensor accelerometer, mGyro, mMagno;
    private SensorManager SensorManager;
    private TextView xAccel, yAccel, zAccel, xGyro, yGyro, zGyro, xMagno, yMagno, zMagno;
    private static final String TAG = "MyActivity";
    private LocationManager locationManager;
    private Location currentBestLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////
        // SENSOR EVENT LISTENER
        //////////////////////////////

        // Assign TextView
        xAccel = (TextView) findViewById(R.id.xAccel);
        yAccel = (TextView) findViewById(R.id.yAccel);
        zAccel = (TextView) findViewById(R.id.zAccel);

        xGyro = (TextView) findViewById(R.id.xGyro);
        yGyro = (TextView) findViewById(R.id.yGyro);
        zGyro = (TextView) findViewById(R.id.zGyro);

        xMagno = (TextView) findViewById(R.id.xMagno);
        yMagno = (TextView) findViewById(R.id.yMagno);
        zMagno = (TextView) findViewById(R.id.zMagno);

        // Create the sensor manager
        Log.d(TAG, "onCreate: Initialising sensor services");
        SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        accelerometer = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            SensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        } else {
            xAccel.setText("Accelerometer Not Supported");
            yAccel.setText("Accelerometer Not Supported");
            zAccel.setText("Accelerometer Not Supported");
        }

        // Gryoscope Sensor
        mGyro = SensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            SensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope listener");
        } else {
            xGyro.setText("Gyro Not Supported");
            yGyro.setText("Gyro Not Supported");
            zGyro.setText("Gyro Not Supported");
        }

        // Magnetometer Sensor
        mMagno = SensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null) {
            SensorManager.registerListener(this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magnetometer listener");
        } else {
            xMagno.setText("Magno Not Supported");
            yMagno.setText("Magno Not Supported");
            zMagno.setText("Magno Not Supported");
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        onLocationChanged(location);

        // Register sensor listener
//        Log.d(TAG, "onCreate: Registered accelerometer listener");

    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.i(TAG, "Longitude: " + longitude + " Latitude :" + latitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // not used
    }

    @Override
    public void onProviderEnabled(String s) {
        // not used
    }

    @Override
    public void onProviderDisabled(String s) {
        // not used
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//            Log.i(TAG, "Accelerometer: X: " + event.values[0] + " Y :" + event.values[1] + " Z: " + event.values[2]);
            xAccel.setText("X: " + event.values[0]);
            yAccel.setText("Y: " + event.values[1]);
            zAccel.setText("Z: " + event.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
//            Log.i(TAG, "Gyroscope: X: " + event.values[0] + " Y :" + event.values[1] + " Z: " + event.values[2]);
            xGyro.setText("X: " + event.values[0]);
            yGyro.setText("Y: " + event.values[1]);
            zGyro.setText("Z: " + event.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            Log.i(TAG, "Magnetometer: X: " + event.values[0] + " Y :" + event.values[1] + " Z: " + event.values[2]);
            xMagno.setText("X: " + event.values[0]);
            yMagno.setText("Y: " + event.values[1]);
            zMagno.setText("Z: " + event.values[2]);
        }

    }

}
