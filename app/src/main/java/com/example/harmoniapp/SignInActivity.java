package com.example.harmoniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harmoniapp.SplashScreenActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn,signUp;
    TextView Email,password;
    FirebaseAuth mAuth ;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        ActionBar a = getSupportActionBar();
        a.setTitle("Sign In ⬇️");
        signIn = findViewById(R.id.submit);
        signUp = findViewById(R.id.signUp);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClick(View view) {
        if(view == signIn){
            if (Email != null && password != null)
                signIn(Email.getText().toString(),password.getText().toString());
        }
        if (view == signUp){
            Intent it = new Intent(this,SignUpActivity.class);
            startActivity(it);
        }
    }
    public void signIn(String email,String password){
        if (email != null && password != null){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent it = new Intent(SignInActivity.this, SplashScreenActivity.class);
                                it.putExtra("Name" ,Email.getText().subSequence(0,3).toString().toUpperCase());
                                startActivity(it);
                            } else {
                                Toast.makeText(SignInActivity.this, "No account found with details entered 😔",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}