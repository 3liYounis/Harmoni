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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button signUp;
    TextView name,email,password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        ActionBar a = getSupportActionBar();
        a.setTitle("Sign Up ⬆️");
        signUp = findViewById(R.id.signUp);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == signUp){
            if (password.getText().length() < 6 || password.getText().toString().contains("!") == false && password.getText().toString().contains("@") == false && password.getText().toString().contains("#") == false &&  password.getText().toString().contains("$")== false){
                Toast.makeText(this,"Weak Password ❌",Toast.LENGTH_LONG).show();
            }
            else{
                createAccount(email.getText().toString(),password.getText().toString());
            }
        }
    }
    public void createAccount(String email, String password){
        if (email != null && password != null){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent it = new Intent(SignUpActivity.this, SplashScreenActivity.class);
                                it.putExtra("NAME",name.getText().toString());
                                startActivity(it);
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}