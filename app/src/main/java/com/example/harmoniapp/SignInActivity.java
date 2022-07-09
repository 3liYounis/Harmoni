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

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn,signUp,google;
    TextView forgotPassword;
    TextInputEditText email,password;
    FirebaseAuth mAuth ;
    FirebaseUser firebaseCurrentUser;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN = 258;
    @Override
    protected void onStart() {
        super.onStart();
//        firebaseCurrentUser = mAuth.getCurrentUser();
//        if(firebaseCurrentUser!=null){
//            Intent it = new Intent(SignInActivity.this, AccountInfoActivity.class);
//            it.putExtra("NAME",firebaseCurrentUser.getDisplayName());
//            it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
//            it.putExtra("PHONENUMBER",firebaseCurrentUser.getPhoneNumber());
//            startActivity(it);
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        google = findViewById(R.id.google_signIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        google.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
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
                            Intent it = new Intent(SignInActivity.this, AccountInfoActivity.class);
                            it.putExtra("NAME",firebaseCurrentUser.getDisplayName());
                            it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
                            it.putExtra("PHONENUMBER",firebaseCurrentUser.getPhoneNumber());
                            startActivity(it);
                        }
                        else {
                            Toast.makeText(SignInActivity.this, "Sorry authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onClick(View view) {
        if(view == signIn){
            if (email != null && password != null)
                signIn(email.getText().toString(),password.getText().toString());
            else
                Toast.makeText(SignInActivity.this, "Please Enter the full details",Toast.LENGTH_SHORT).show();
        }
        if (view == signUp){
            Intent it = new Intent(this,SignUpActivity.class);
            startActivity(it);
        }
        if (view == google) {
            signIn();
        }
        if (view == forgotPassword){
            Intent it = new Intent(SignInActivity.this,ForgotPassword.class);
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
                                mAuth.getUid();
                                firebaseCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (firebaseCurrentUser != null){
                                    Intent it = new Intent(SignInActivity.this, AccountInfoActivity.class);
                                    // DISPLAYY NAME && PHONE NUMBER !!!
                                    it.putExtra("NAME",firebaseCurrentUser.getEmail().substring(0,4));
                                    it.putExtra("EMAIL",firebaseCurrentUser.getEmail());
                                    it.putExtra("PHONENUMBER",firebaseCurrentUser.getPhoneNumber());
                                    startActivity(it);
                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "No account found with details entered 😔",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}