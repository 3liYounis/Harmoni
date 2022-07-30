package com.example.harmoniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{
    TextInputLayout emailLayout;
    TextInputEditText email;
    Button resetPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        emailLayout = findViewById(R.id.emailLayout);
        resetPassword = findViewById(R.id.resetPassword);
        resetPassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == resetPassword){
            resetPassword(email.getText().toString());
        }
    }
    private void resetPassword(String recoverEmail){
        if (recoverEmail.isEmpty()){
            emailLayout.setError("Please enter your email!");
            emailLayout.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(recoverEmail).matches()){
            emailLayout.setError("Please enter a valid email");
            emailLayout.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(recoverEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Check your email to reset your password ✅ ",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this,"Try again, something wrong happened ❌",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}