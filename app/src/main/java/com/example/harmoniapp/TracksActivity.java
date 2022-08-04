package com.example.harmoniapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;

public class TracksActivity extends AppCompatActivity implements  View.OnClickListener {
    Button mindfulness, creative, sport, music;
    FirebaseAuth mAuth;

    BottomNavigationItemView dashBoard, progressMap,account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_tracks);
        mindfulness = findViewById(R.id.WritingAct);
        creative = findViewById(R.id.DoodlingAct);
        sport = findViewById(R.id.DancingAct);
        music = findViewById(R.id.JammingAct);
        dashBoard = findViewById(R.id.dashBoard);
        progressMap = findViewById(R.id.progressMap);
        account = findViewById(R.id.account);
        dashBoard.setOnClickListener(this);
        progressMap.setOnClickListener(this);
        account.setOnClickListener(this);
        mindfulness.setOnClickListener(this);
        creative.setOnClickListener(this);
        music.setOnClickListener(this);
        sport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == progressMap) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == account){
            Intent ProgressButtonIntent = new Intent(this, AccountInfoActivity.class);
            startActivity(ProgressButtonIntent);
        }
//        if (view == mindfulness) {
//            Intent Track1Activity1Intent = new Intent(this,WritingActivity.class);
//            startActivity(Track1Activity1Intent);
//        }
        if (view == creative) {
            Intent Track1Activity1Intent = new Intent(this,CreativeActivity.class);
            startActivity(Track1Activity1Intent);
        }
//        if (view == music) {
//            Intent Track1Activity1Intent = new Intent(this,Jamming.class);
//            startActivity(Track1Activity1Intent);
//        }
//        if (view == sport) {
//            Intent Track1Activity1Intent = new Intent(this,DancingActivity.class);
//            startActivity(Track1Activity1Intent);
//        }
    }
}