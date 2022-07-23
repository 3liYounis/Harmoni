package com.example.harmoniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountInfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name,email,coins,activitiesDone;
    Button home;
    FirebaseAuth mAuth;
    FirebaseUser firebaseCurrentUser;
    HarmoniUser harmoniUser;
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        firebaseCurrentUser = mAuth.getCurrentUser();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        coins = findViewById(R.id.coins);
        activitiesDone = findViewById(R.id.activitiesDone);
        home = findViewById(R.id.home);
        home.setOnClickListener(this);
        email.setText(firebaseCurrentUser.getEmail());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("uId").toString().equals(firebaseCurrentUser.getUid())){
                        name.setText(ds.child("userName").toString());
                        email.setText(ds.child("email").toString());
                        coins.setText(ds.child("coins").toString());
                        activitiesDone.setText(ds.child("activitiesDone").toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view == home){
            Intent it = new Intent(AccountInfoActivity.this, HomeActivity.class);
            startActivity(it);
        }
    }
}