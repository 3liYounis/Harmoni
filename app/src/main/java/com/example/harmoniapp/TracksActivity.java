package com.example.harmoniapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TracksActivity extends AppCompatActivity implements  View.OnClickListener {
    ImageButton mindfulness, creative, sport, music;
    FirebaseAuth mAuth;
    TextView welcome;
    BottomNavigationItemView dashBoard, progressMap,account;
    FirebaseUser firebaseCurrentUser;
    FirebaseDatabase database;
    DatabaseReference ref;
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
        welcome = findViewById(R.id.welcome);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        firebaseCurrentUser = mAuth.getCurrentUser();
        ref.child(firebaseCurrentUser.getUid()).child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                welcome.setText("Hello "+ snapshot.getValue(String.class) + "\nChoose a track !");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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