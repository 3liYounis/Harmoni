package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener {
    Button main_activity, side_activity1, side_activity2, side_activity3, side_activity4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_current);
        main_activity = findViewById(R.id.main_activity);
        side_activity1 = findViewById(R.id.side_activity1);
        side_activity2 = findViewById(R.id.side_activity2);
        side_activity3 = findViewById(R.id.side_activity3);
        side_activity4 = findViewById(R.id.side_activity4);
        main_activity.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == main_activity) {
           Intent MainActivityIntent = new Intent(this, MissionViewActivity.class);
            startActivity(MainActivityIntent);
        }

    }
}

