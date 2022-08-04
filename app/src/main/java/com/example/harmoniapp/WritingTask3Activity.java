package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingTask3Activity extends AppCompatActivity implements View.OnClickListener{
    TextView InstructionsWritingAct, fillinBlank1 ,fillinBlank2, Blank;
    EditText Edittext1;
    ImageButton DoneWritingAct;
    BottomNavigationItemView progressMap, dashBoard,account;
    static  int updateCounter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_task3);
        InstructionsWritingAct = findViewById(R.id.InstructionsWritingAct);
        DoneWritingAct = findViewById(R.id.DoneWritingAct);
        Edittext1 = findViewById(R.id.Edittext1);
        fillinBlank1 = findViewById(R.id.fillinBlank1);
        fillinBlank2 = findViewById(R.id.fillinBlank2);
        Blank =  findViewById(R.id.Blank);
        dashBoard = findViewById(R.id.dashBoard);
        progressMap = findViewById(R.id.progressMap);
        account = findViewById(R.id.account);
        dashBoard.setOnClickListener(this);
        progressMap.setOnClickListener(this);
        account.setOnClickListener(this);
        DoneWritingAct.setOnClickListener(this);

        Intent ResultIntent = getIntent();
        Blank.setText(ResultIntent.getStringExtra("CHOICE"));


    }

    @Override
    public void onClick(View view) {
        if (view == DoneWritingAct) {
            updateDatabaseActDone();
            updateCounter=0;
            Intent DoneWritingActIntent = new Intent(this, CreativeActivity.class);
            startActivity(DoneWritingActIntent);
        }
        if (view == progressMap) {
            Intent ProgressButtonIntent = new Intent(this, ProgressActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == dashBoard) {
            Intent ProgressButtonIntent = new Intent(this, TracksActivity.class);
            startActivity(ProgressButtonIntent);
        }
        if (view == account) {
            Intent accountIntent = new Intent(this, AccountInfoActivity.class);
            startActivity(accountIntent);
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