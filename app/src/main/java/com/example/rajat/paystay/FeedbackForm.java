package com.example.rajat.paystay;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.UserDataHandler;

import DatabaseHelpers.DatabaseHelper;
import DatabaseHelpers.FeedbackContact;
import DatabaseHelpers.FeedbackDatabaseHelper;

public class FeedbackForm extends AppCompatActivity  {

    FeedbackDatabaseHelper feedbackDatabaseHelper=new FeedbackDatabaseHelper(this);
    SQLiteDatabase db;
    EditText name;
    EditText email;
    EditText comments;
    Button send;
    //  Spinner feedback;
    RatingBar ratingBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        textView=(TextView)findViewById(R.id.feedback);
        Animation animation1= AnimationUtils.loadAnimation(FeedbackForm.this, R.anim.blink1);
        textView.startAnimation(animation1);
        name = (EditText) findViewById(R.id.EditTextName);
        email = (EditText) findViewById(R.id.EditTextEmail);
        comments = (EditText) findViewById(R.id.EditTextFeedbackBody);
        send = (Button) findViewById(R.id.ButtonSendFeedback);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
       // Toast.makeText(FeedbackForm.this, "On Create Done", Toast.LENGTH_SHORT).show();

    }

    public void sendFeedback(View v)
    {
       // Toast.makeText(FeedbackForm.this, "Send Feedback started", Toast.LENGTH_SHORT).show();
        final float rating1=ratingBar.getRating();
        String r1= String.valueOf(rating1);
        final String name1=name.getText().toString();
        final String email1=email.getText().toString();

        final String comments1=comments.getText().toString();
//        feedbackDatabaseHelper.insertFeedbackData(name1,email1,comments1,r1, db);
        FeedbackContact ca = new FeedbackContact();

        ca.setName(name1);
        ca.setEmail(email1);
        ca.setComment(comments1);
        ca.setRating(r1);
        feedbackDatabaseHelper.insertFeedback(ca);
        Toast.makeText(this, "Feedback Submitted Successfully. Thanks for your valuable support", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
        startActivity(i);


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
        startActivity(i);
    }












}



