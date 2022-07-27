package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Jamming extends AppCompatActivity implements View.OnClickListener {

    androidx.appcompat.widget.AppCompatButton DoneJamming;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dancing);
        getSupportActionBar().hide();
        DoneJamming = findViewById(R.id.DoneDancingAct);

        DoneJamming.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == DoneJamming) {

            sharedPreferences = getSharedPreferences("Jamming",MODE_PRIVATE);
            boolean isDone = sharedPreferences.getBoolean("ISDONE",true);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("ISDONE",true);
            editor.commit();
            Intent DoneWritingActIntent = new Intent(this, TracksActivity.class);
            startActivity(DoneWritingActIntent);
            finish();
        }
    }

}