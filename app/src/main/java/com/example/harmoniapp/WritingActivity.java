package com.example.harmoniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingActivity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationItemView dashBoard, progressMap,account;
    RadioButton WeirdButton, BadButton, MehButton , GoodButton;
    String userChoice ="";
    EditText Blank;
    ImageButton ContinueButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        getSupportActionBar().hide();
        ContinueButton =  findViewById(R.id.ContinueButton);
        dashBoard = findViewById(R.id.dashBoard);
        progressMap = findViewById(R.id.progressMap);
        account = findViewById(R.id.account);
        WeirdButton = findViewById(R.id.WeirdButton);
        GoodButton = findViewById(R.id.GoodButton);
        BadButton = findViewById(R.id.BadButton);
        MehButton = findViewById(R.id.MehButton);
//        FillInBlank1 = findViewById(R.id.FillinBlank1);
//        FillInBlank2 = findViewById(R.id.FillinBlank2);
        Blank =  findViewById(R.id.Blank);
        GoodButton.setOnClickListener(this);
        WeirdButton.setOnClickListener(this);
        BadButton.setOnClickListener(this);
        MehButton.setOnClickListener(this);
        dashBoard.setOnClickListener(this);
        progressMap.setOnClickListener(this);
        account.setOnClickListener(this);
        ContinueButton.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        if (view == dashBoard) {
            Intent dashBoardIntent = new Intent(this, TracksActivity.class);
            startActivity(dashBoardIntent);
        }
        if (view == progressMap) {
            Intent progressMapIntent = new Intent(this, ProgressActivity.class);
            startActivity(progressMapIntent);
        }
        if (view == account) {
            Intent accountIntent = new Intent(this, AccountInfoActivity.class);
            startActivity(accountIntent);
        }
        Intent it = new Intent(this,WritingTask3Activity.class);
        if (view == GoodButton) {
            Blank.setText("Good");
            userChoice = "Good";
        }
        if (view == WeirdButton) {
            Blank.setText("Weird");
            userChoice = "Weird";
        }
        if (view == BadButton) {
            Blank.setText("Bad");
            userChoice = "Bad";

        }
        if (view == MehButton) {
            Blank.setText("Meh");
            userChoice = "Meh";
        }
        if (view == ContinueButton) {
            it.putExtra("CHOICE",userChoice);
            startActivity(it);
        }









    }

}










