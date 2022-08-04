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
    TextView FillInBlank1, FillInBlank2;
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
        if (view == ContinueButton) {
            Intent ContinueButtonIntent = new Intent(this, WritingTask2Activity.class);
            startActivity(ContinueButtonIntent);
        }
        Intent it = new Intent(this,WritingTask3Activity.class);
        if (view == GoodButton) {
            Blank.setText("Good");
            it.putExtra("CHOICE","good");
            startActivity(it);
        }
        if (view == WeirdButton) {
            Blank.setText("Weird");
            it.putExtra("CHOICE","weird");
            startActivity(it);
        }
        if (view == BadButton) {
            Blank.setText("Bad");
            it.putExtra("CHOICE","bad");
            startActivity(it);

        }
        if (view == MehButton) {
            Blank.setText("Meh");
            it.putExtra("CHOICE","meh");
            startActivity(it);
        }









    }

}










