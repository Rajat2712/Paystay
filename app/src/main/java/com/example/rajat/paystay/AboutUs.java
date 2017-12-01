package com.example.rajat.paystay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
    TextView textView,tv11,tv13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        textView=(TextView)findViewById(R.id.textView4);
        tv11=(TextView)findViewById(R.id.textView11);
        tv13=(TextView)findViewById(R.id.textView13);
        Animation animation1= AnimationUtils.loadAnimation(AboutUs.this, R.anim.blink1);
        textView.startAnimation(animation1);
        tv11.setClickable(true);
        tv11.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://facebook.com/rgrgrajat1'> Rajat Gupta </a>";
        tv11.setText(Html.fromHtml(text));

       
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
        startActivity(i);
    }



}
