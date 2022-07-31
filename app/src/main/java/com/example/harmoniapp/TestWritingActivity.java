package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TestWritingActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton GoodButton;
    EditText EmotionName;
    TextView SideActivityName, WritingActQuo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_writing);
        GoodButton = findViewById(R.id.GoodButton);
        EmotionName = findViewById(R.id.EmotionName);
        SideActivityName = findViewById(R.id.SideActivityName);
        WritingActQuo =  findViewById(R.id.WritingActQuo);

    }

    @Override
    public void onClick(View view) {
        if (view == GoodButton) {
            String GoodButtonId = (String) GoodButton.getText();
            EmotionName.setText(GoodButtonId);
        }

    }

}