package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class TestWritingActivity extends AppCompatActivity implements View.OnClickListener {
RadioButton GoodButton;
EditText EmotionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_writing);
        GoodButton = findViewById(R.id.GoodButton);
        //EmotionName = findViewById(R.id.EmotionName);

    }

    @Override
    public void onClick(View view) {
        if (view == GoodButton) {

        }
    }
}