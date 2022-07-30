package com.example.harmoniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingActivity extends AppCompatActivity implements View.OnClickListener {
    Button DoneWritingAct;
    TextView SideActivityName, WritingActQuo;
    BottomNavigationItemView dashBoard, progressMap, achievements,account;
    static  int updateCounter =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        getSupportActionBar().hide();
        DoneWritingAct = findViewById(R.id.DoneWritingAct);
        SideActivityName = findViewById(R.id.SideActivityName);
        WritingActQuo = findViewById(R.id.WritingActQuo);
        DoneWritingAct.setOnClickListener(this);
        dashBoard = findViewById(R.id.dashBoard);
        progressMap = findViewById(R.id.progressMap);
        achievements = findViewById(R.id.goalsAchieved);
        account = findViewById(R.id.account);
        dashBoard.setOnClickListener(this);
        progressMap.setOnClickListener(this);
        achievements.setOnClickListener(this);
        account.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        if (view == dashBoard) {
            Intent HomeButtonIntent = new Intent(this, TracksActivity.class);
            startActivity(HomeButtonIntent);
        }
        if (view == progressMap) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == DoneWritingAct) {
            updateDatabaseActDone();
            updateCounter=0;
            Intent ProgressButtonIntent = new Intent(this, CreativeActivity.class);
            startActivity(ProgressButtonIntent);
        }
    }
    public void updateDatabaseActDone(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseCurrentUser = mAuth.getCurrentUser();
        ref.child(firebaseCurrentUser.getUid()).child("activitiesDone").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (updateCounter == 0){
                int actDone = snapshot.getValue(Integer.class);
                ref.child(firebaseCurrentUser.getUid()).child("activitiesDone").setValue(actDone+1);
                updateCounter++;
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
        });
    }
}










