package com.example.harmoniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DrawingActivity extends AppCompatActivity {


static  int updateCounter=0;
    TextView textView,sectext;
    androidx.appcompat.widget.AppCompatButton Continue;
    int Page = 1;
    ImageView slider1;
    ImageView slider2;
    ImageView slider3;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    Button Pic1,Pic2,Pic3,Pic4;
    Animation animation;
    int TheChosenPic ;








    @Override
    protected void onStart() {
        super.onStart();




        Pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheChosenPic  =1;
                Pic1.setBackgroundResource(R.drawable.background_empty);
                Pic2.setBackgroundResource(R.color.trans);
                Pic3.setBackgroundResource(R.color.trans);
                Pic4.setBackgroundResource(R.color.trans);
                if(!Continue.isShown()) {
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.another_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.VISIBLE);
                }

            }
        });
        Pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheChosenPic  =2;
                Pic2.setBackgroundResource(R.drawable.background_empty);
                Pic1.setBackgroundResource(R.color.trans);
                Pic3.setBackgroundResource(R.color.trans);
                Pic4.setBackgroundResource(R.color.trans);
                if(!Continue.isShown()) {
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.another_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.VISIBLE);
                }
            }
        });
        Pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheChosenPic  =3;
                Pic3.setBackgroundResource(R.drawable.background_empty);
                Pic2.setBackgroundResource(R.color.trans);
                Pic1.setBackgroundResource(R.color.trans);
                Pic4.setBackgroundResource(R.color.trans);
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.another_anim);
                if(!Continue.isShown()) {
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.another_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.VISIBLE);
                }
            }
        });
        Pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheChosenPic  =4;
                Pic4.setBackgroundResource(R.drawable.background_empty);
                Pic2.setBackgroundResource(R.color.trans);
                Pic3.setBackgroundResource(R.color.trans);
                Pic1.setBackgroundResource(R.color.trans);
                if(!Continue.isShown()) {
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.another_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.VISIBLE);
                }
            }
        });
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(Page == 1)
                {
                    Page++;
                    slider1.setImageResource(R.drawable.background_empty);
                    slider2.setImageResource(R.drawable.background);
                    animateTextViewTextChange(textView,600,"What doodle best\n" +
                            "describes your mood:");
                    animateTextViewTextChange(sectext,600,"");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_anim);
                    image1.setAnimation(animation);
                    image1.setVisibility(View.INVISIBLE);
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.INVISIBLE);
                    Continue.setClickable(true);
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.another_anim);
                    image2.setAnimation(animation);
                    image2.setVisibility(View.VISIBLE);
                    Pic1.setClickable(true);
                    Pic2.setClickable(true);
                    Pic3.setClickable(true);
                    Pic4.setClickable(true);
                    Pic1.setVisibility(View.VISIBLE);
                    Pic2.setVisibility(View.VISIBLE);
                    Pic3.setVisibility(View.VISIBLE);
                    Pic4.setVisibility(View.VISIBLE);
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_anim);
                    Continue.setAnimation(animation);
                    Continue.setVisibility(View.INVISIBLE);
                }else
                if(Page == 2)
                {
                    Pic1.setClickable(false);
                    Pic2.setClickable(false);
                    Pic3.setClickable(false);
                    Pic4.setClickable(false);
                    Page++;
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                    image2.setAnimation(animation);
                    image2.setVisibility(View.INVISIBLE);
                    slider2.setImageResource(R.drawable.background_empty);
                    slider3.setImageResource(R.drawable.background);
                    animateTextViewTextChange(textView,600,"Draw the doodle\n" +
                            "you picked:");
                    animateTextViewTextChange(sectext,600,"Draw it 5 times\n" +
                            "on a sheet of paper");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.another_anim);
                    image3.setAnimation(animation);
                    image3.setVisibility(View.VISIBLE);
                    Pic2.setBackgroundResource(R.color.trans);
                    Pic3.setBackgroundResource(R.color.trans);
                    Pic1.setBackgroundResource(R.color.trans);
                    Pic4.setBackgroundResource(R.color.trans);
                }else
                if(Page == 3)
                {
                        updateDatabaseActDone();
                        updateCounter=0;
                        Intent it = new Intent(DrawingActivity.this,TracksActivity.class);
                        startActivity(it);
                }
            }


        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        Continue = findViewById(R.id.nextt);
        slider1 = findViewById(R.id.slider1);
        Pic1 = findViewById(R.id.choice1);
        Pic2 = findViewById(R.id.choice2);
        Pic3 = findViewById(R.id.choice3);
        Pic4 = findViewById(R.id.choice4);
        slider2 = findViewById(R.id.slider2);
        slider3 = findViewById(R.id.slider3);
        textView = findViewById(R.id.Toptext);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        sectext = findViewById(R.id.sectext);
        TheChosenPic = 0;

    }

    private void crossfade(ImageView v) {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        v.setAlpha(0f);
        v.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        v.animate()
                .alpha(1f)
                .setDuration(600)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        v.animate()
                .alpha(0f)
                .setDuration(6000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setVisibility(View.GONE);
                    }
                });
    }
    public static void animateTextViewTextChange(final TextView textView,
                                                 final int duration, final String newText) {
        textView.animate().alpha(0f).setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        textView.setText(newText);
                        textView.animate().alpha(1f).setDuration(duration)
                                .start();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
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