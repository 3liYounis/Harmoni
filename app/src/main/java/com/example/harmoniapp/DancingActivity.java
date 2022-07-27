package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class DancingActivity extends AppCompatActivity implements View.OnClickListener {

    androidx.appcompat.widget.AppCompatButton DoneDancing;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dancing);
        getSupportActionBar().hide();
        DoneDancing = findViewById(R.id.DoneDancingAct);

        DoneDancing.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == DoneDancing) {

            sharedPreferences = getSharedPreferences("Dancing",MODE_PRIVATE);
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