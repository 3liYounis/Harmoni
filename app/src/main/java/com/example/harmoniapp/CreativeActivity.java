package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class CreativeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton charlie,reflect;
    BottomNavigationItemView progressMap, dashBoard,account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_creative);
        charlie = findViewById(R.id.charlie);
        reflect = findViewById(R.id.reflect);
        dashBoard = findViewById(R.id.dashBoard);
        progressMap = findViewById(R.id.progressMap);
        account = findViewById(R.id.account);
        dashBoard.setOnClickListener(this);
        progressMap.setOnClickListener(this);
        account.setOnClickListener(this);
        charlie.setOnClickListener(this);
        reflect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == charlie){
            Intent it = new Intent(this, DrawingActivity.class);
            startActivity(it);
        }
        if (view == reflect){
            Intent it = new Intent(this,WritingActivity.class);
            startActivity(it);
        }
        if (view == progressMap) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == dashBoard) {
            Intent ProgressButtonIntent = new Intent(this, TracksActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == account) {
            Intent accountIntent = new Intent(this, AccountInfoActivity.class);
            startActivity(accountIntent);
        }
    }
}