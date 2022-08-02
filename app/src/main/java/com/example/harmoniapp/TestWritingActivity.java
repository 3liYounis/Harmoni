package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TestWritingActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton  WeirdButton, BadButton, MehButton , GoodButton;
    TextView FillInBlank1, FillInBlank2, Blank, SideActivityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_writing);
        WeirdButton = findViewById(R.id.WeirdButton);
        GoodButton = findViewById(R.id.GoodButton);
        BadButton = findViewById(R.id.BadButton);
        MehButton = findViewById(R.id.MehButton);
        FillInBlank1 = findViewById(R.id.FillinBlank1);
        FillInBlank2 = findViewById(R.id.FillinBlank2);
        Blank =  findViewById(R.id.Blank);
        SideActivityName = findViewById(R.id.SideActivityName);
        GoodButton.setOnClickListener(this);
        WeirdButton.setOnClickListener(this);
        BadButton.setOnClickListener(this);
        MehButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == GoodButton) {
            Blank.setText("Good");
        }
        if (view == WeirdButton) {
            Blank.setText("Weird");
        }
        if (view == BadButton) {
            Blank.setText("Bad");
        }
        if (view == MehButton) {
            Blank.setText("Meh");
        }
    }

}