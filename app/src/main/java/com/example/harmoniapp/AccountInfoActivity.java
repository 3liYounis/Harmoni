package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountInfoActivity extends AppCompatActivity {
    TextView name,email,phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        getSupportActionBar().hide();
        Intent signPageInfo = getIntent();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        GoogleSignInAccount acc1 = GoogleSignIn.getLastSignedInAccount(this);
        name.setText(signPageInfo.getStringExtra("NAME"));
        email.setText(signPageInfo.getStringExtra("EMAIL"));
        phoneNumber.setText(signPageInfo.getStringExtra("PHONENUMBER"));

    }
}