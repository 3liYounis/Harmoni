package com.example.harmoniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener {
    TextView ProgressHeadline, ProgressTrack;
    ProgressBar Track1ProgressBar;
    ImageView animated;
    BottomNavigationItemView ProgressButton, HomeButton, AchievementsButton;
    FirebaseAuth mAuth;
    FirebaseUser firebaseCurrentUser;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        firebaseCurrentUser = mAuth.getCurrentUser();
        setContentView(R.layout.activity_progress);
        Track1ProgressBar = findViewById(R.id.Track1ProgressBar);
        ProgressHeadline = findViewById(R.id.ProgressHeadline);
        HomeButton = findViewById(R.id.HomeButton);
        AchievementsButton= findViewById(R.id.AchievementsButton);
        ProgressButton = findViewById(R.id.ProgressButton);
        animated = findViewById(R.id.animated);
        HomeButton.setOnClickListener(this);
        ProgressButton.setOnClickListener(this);
        ref.child(firebaseCurrentUser.getUid()).child("activitiesDone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int progress = snapshot.getValue(Integer.class)*50;
                Track1ProgressBar.setProgress(progress);
                if (progress == 100){
                    AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) animated.getDrawable();
                    drawable.start();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == HomeButton) {
            Intent HomeButtonIntent = new Intent(this, TracksActivity.class);
            startActivity(HomeButtonIntent);
        }
        if (view == ProgressButton) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
    }
}


