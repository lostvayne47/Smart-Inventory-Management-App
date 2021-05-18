package com.example.edai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variables
    android.view.animation.Animation topAnim;
    android.view.animation.Animation bottomAnim;
    ImageView image;
    TextView smart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.logo);
        smart = findViewById(R.id.textView);
        image.setAnimation(topAnim);
        smart.setAnimation(bottomAnim);

        int SPLASH_SCREEN = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent animIntent = new Intent(MainActivity.this, signup.class);
                startActivity(animIntent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
