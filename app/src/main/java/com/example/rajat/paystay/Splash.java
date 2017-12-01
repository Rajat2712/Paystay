package com.example.rajat.paystay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity {
    private int SPLASH_TIME_OUT=2500;
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView)findViewById(R.id.splashView);
        textView=(TextView)findViewById(R.id.textView3);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        Animation animation= AnimationUtils.loadAnimation(Splash.this, R.anim.scale_animation);
        imageView.startAnimation(animation);
        Animation animation1= AnimationUtils.loadAnimation(Splash.this,R.anim.scale_animation);
        textView.startAnimation(animation1);

    }


}

