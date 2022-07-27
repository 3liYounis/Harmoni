package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class CreativeActivity extends AppCompatActivity implements View.OnClickListener {
    Button charlie,reflect;
    BottomNavigationItemView ProgressButton, TracksButton, AchievementsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_creative);
        charlie = findViewById(R.id.charlie);
        reflect = findViewById(R.id.reflect);
        TracksButton = findViewById(R.id.HomeButton);
        ProgressButton = findViewById(R.id.ProgressButton);
        TracksButton.setOnClickListener(this);
        ProgressButton.setOnClickListener(this);
        charlie.setOnClickListener(this);
        reflect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == charlie){
            Intent it = new Intent(this,DrawingActivity.class);
            startActivity(it);
        }
        if (view == reflect){
            Intent it = new Intent(this,WritingActivity.class);
            startActivity(it);
        }
        if (view == ProgressButton) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == TracksButton) {
            Intent ProgressButtonIntent = new Intent(this, TracksActivity.class);
            startActivity(ProgressButtonIntent);
        }
    }
}