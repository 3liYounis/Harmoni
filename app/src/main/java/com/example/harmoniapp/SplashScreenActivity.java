package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseUser firebaseCurrentUser;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firebaseCurrentUser = mAuth.getCurrentUser();
                if(firebaseCurrentUser!=null){
                    Intent it = new Intent(SplashScreenActivity.this, SignInActivity.class);
                    startActivity(it);
                }
                else{
                    Intent it = new Intent(SplashScreenActivity.this, SignInActivity.class);
                    startActivity(it);
                }
            }
        },1000);
    }
}