/* Change gabrieldiogo.testingapp to name of your app */
package com.example.gabrieldiogo.testingapp;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/* Place the following commented lines in the manifest file */
/* <uses-permission android:name="android.permission.BLUETOOTH"/> */
/* <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/> */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("BluetoothState",BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        setBluetooth(true);
        Log.v("BluetoothState",BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
    }

    public static boolean setBluetooth(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isEnabled = bluetoothAdapter.isEnabled();
        if (enable && !isEnabled) {
            Log.v("BluetoothState","ON");
            return bluetoothAdapter.enable();
        }
        else if(!enable && isEnabled) {
            Log.v("BluetoothState","OFF");
            return bluetoothAdapter.disable();
        }
        // No need to change bluetooth state
        return true;
    }

}