package com.example.rajat.paystay;

import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Feedback(View v)
    {
        Intent intent=new Intent(this,FeedbackForm.class);
        startActivity(intent);
    }
    public void fetchFeedback(View v)
    {
        Intent intent=new Intent(this,FeedbackDetail.class);
        startActivity(intent);

    }
}
