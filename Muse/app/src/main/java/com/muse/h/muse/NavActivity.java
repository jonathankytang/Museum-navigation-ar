package com.muse.h.muse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NavActivity extends AppCompatActivity {

    private TextView Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Map = (TextView)findViewById(R.id.mapLink);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
