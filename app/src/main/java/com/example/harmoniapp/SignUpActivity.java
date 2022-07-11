package com.example.harmoniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button signUp,google;
    TextInputLayout nameLayout,emailLayout,passwordLayout,phoneNumberLayout;
    TextInputEditText name,email,password,phoneNumber;
    FirebaseAuth mAuth;
    FirebaseUser firebaseCurrentUser;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN = 258;
    @Override
    protected void onStart() {
        super.onStart();
//        firebaseCurrentUser = mAuth.getCurrentUser();
//        if(firebaseCurrentUser!=null){
//            Intent it = new Intent(SignUpActivity.this, AccountInfoActivity.class);
//            it.putExtra("NAME",firebaseCurrentUser.getDisplayName());
//            it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
//            it.putExtra("PHONENUMBER",phoneNumber.getText());
//            startActivity(it);
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        signUp = findViewById(R.id.signUp);
        google = findViewById(R.id.google_signUp);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        nameLayout = findViewById(R.id.nameLayout);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        phoneNumberLayout = findViewById(R.id.phoneNumberLayout);
        signUp.setOnClickListener(this);
        google.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        createRequest();
    }
    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    @Override
    public void onClick(View view) {
        if (view == signUp){
            if (name.getText().toString().isEmpty()){
                nameLayout.setError("Enter a Username !");
            }
            if (email.getText().toString().isEmpty()){
                emailLayout.setError("Enter an Email !");
            }
            if (!email.getText().toString().isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                emailLayout.setError("Enter a valid Email !");
                email.requestFocus();
            }
            if (phoneNumber.getText().toString().isEmpty()){
                phoneNumberLayout.setError("Enter a Phone Number !");
            }
            if (phoneNumber.getText().toString().length() != 10 && !phoneNumber.getText().toString().isEmpty()){
                phoneNumberLayout.setError("Enter a valid Phone Number !");
            }
            if (password.getText().toString().isEmpty()){
                passwordLayout.setError("Enter the password ! ");
            }
            if (password.getText().length() < 8 && !password.getText().toString().isEmpty()){
                passwordLayout.setError("Enter a strong password ! [0-9]/[!-+] ");
            }
            if (password.getText().length() > 8 && phoneNumber.getText().toString().length()==10 &&Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() ){
                createAccount(email.getText().toString(),password.getText().toString());
            }
        }
        if (view == google){
            signIn();
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseCurrentUser = mAuth.getCurrentUser();
                            Intent it = new Intent(SignUpActivity.this, AccountInfoActivity.class);
                            it.putExtra("NAME",firebaseCurrentUser.getDisplayName());
                            it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
                            it.putExtra("PHONENUMBER",phoneNumber.getText());
                            startActivity(it);
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Sorry authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void createAccount(String email, String password){
        if (email != null && password != null){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (firebaseCurrentUser != null){
                                    Intent it = new Intent(SignUpActivity.this, AccountInfoActivity.class);
                                    it.putExtra("NAME",name.getText().toString());
                                    it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
                                    it.putExtra("PHONENUMBER",phoneNumber.getText().toString());
                                    startActivity(it);
                                }
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