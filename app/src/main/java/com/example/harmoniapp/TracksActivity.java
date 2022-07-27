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
    Button mindfulness, creative, sport, music,signOut;
    TextView TrackName;
    FirebaseAuth mAuth;

    BottomNavigationItemView ProgressButton, HomeButton, AchievementsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_tracks);
        TrackName = findViewById(R.id.TrackName);
        mindfulness = findViewById(R.id.WritingAct);
        creative = findViewById(R.id.DoodlingAct);
        sport = findViewById(R.id.DancingAct);
        music = findViewById(R.id.JammingAct);
        ProgressButton = findViewById(R.id.ProgressButton);
        AchievementsButton = findViewById(R.id.AchievementsButton);
        HomeButton = findViewById(R.id.HomeButton);
        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(this);
        ProgressButton.setOnClickListener(this);
        mindfulness.setOnClickListener(this);
        HomeButton.setOnClickListener(this);
        creative.setOnClickListener(this);
        music.setOnClickListener(this);
        sport.setOnClickListener(this);

    }


    public void toast(String a,int sec) // short way for you kings
    {
        try {
            Toast.makeText(getApplicationContext(), a, sec * 1000).show();

        }catch (Exception e)
        {
        }
    }

    @Override
    public void onClick(View view) {
        if (view == signOut){
            mAuth.signOut();
            Intent ProgressButtonIntent = new Intent(this, SplashScreenActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == ProgressButton) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
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