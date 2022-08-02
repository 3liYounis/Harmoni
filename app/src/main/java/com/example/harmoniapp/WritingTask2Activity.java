package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class WritingTask2Activity extends AppCompatActivity implements View.OnClickListener{
TextView InstructionsWritingAct;
EditText Edittext1, Edittext2, Edittext3;
ImageButton ContinueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_task2);
        InstructionsWritingAct = findViewById(R.id.InstructionsWritingAct);
        ContinueButton = findViewById(R.id.ContinueButton);
        Edittext1 = findViewById(R.id.Edittext1);
        Edittext2 = findViewById(R.id.Edittext2);
        Edittext3 = findViewById(R.id.Edittext3);

        ContinueButton.setOnClickListener(this);
        Edittext1.setOnClickListener(this);
        Edittext2.setOnClickListener(this);
        Edittext3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == ContinueButton) {
            Intent ContinueButtonIntent = new Intent(this, WritingTask2Activity.class);
            startActivity(ContinueButtonIntent);
        }

    }
}