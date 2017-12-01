package com.example.rajat.paystay;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DatabaseHelpers.FeedbackContact;
import DatabaseHelpers.FeedbackDatabaseHelper;

public class FeedbackDetail extends AppCompatActivity {
    EditText id, name, comment, rate;
    Button button;
    private SQLiteDatabase db;

    private Cursor c;
    FeedbackDatabaseHelper feedbackDatabaseHelper=new FeedbackDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_detail);
        id=(EditText)findViewById(R.id.etid);
        name=(EditText)findViewById(R.id.etName);
        comment=(EditText)findViewById(R.id.etcomment);
        rate=(EditText)findViewById(R.id.etrate);
        button=(Button)findViewById(R.id.gettingFeedback);
        db = openOrCreateDatabase("feedbackInformation", Context.MODE_PRIVATE, null);
    }
    public void fetchData(FeedbackContact fc)

    {
        name.setText(fc.getName());
        comment.setText(fc.getComment());
        rate.setText(fc.getRating());
    }
}
