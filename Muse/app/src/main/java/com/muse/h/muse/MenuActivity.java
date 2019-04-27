package com.muse.h.muse;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;

public class MenuActivity extends AppCompatActivity {

    private TextView Map;
    private TextView Nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Map = (TextView)findViewById(R.id.mapLink);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        Nav = (TextView)findViewById(R.id.navLink);

        Nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes to ar fragment
                enableArButton();
            }
        });
    }

    void enableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    enableArButton();
                }
            }, 200);
        }
        if (availability.isSupported()) {
            Intent intent = new Intent(MenuActivity.this, ARActivity.class);
            startActivity(intent);
            // indicator on the button.
        } else { // Unsupported or unknown.
            Context context = getApplicationContext();
            CharSequence text = "Android version not compatible";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
            toast.show();

        }
    }
}
