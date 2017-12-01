package com.example.rajat.paystay;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import DatabaseHelpers.BookingDatabase;
import DatabaseHelpers.FeedbackContact;
import DatabaseHelpers.FeedbackDatabaseHelper;
import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;

public class AdminSection extends AppCompatActivity {
    FeedbackDatabaseHelper feedbackDatabaseHelper=new FeedbackDatabaseHelper(this);
    BookingDatabase bookingDatabase=new BookingDatabase(this);
    PgDatabaseHelper pgDatabaseHelper=new PgDatabaseHelper(this);
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_section);
        db = openOrCreateDatabase("feedbackInformation", Context.MODE_PRIVATE, null);

    }
    public void Back(View view)
    {
        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
    }
  /*  public  void seePgList(View v)
    {
        Cursor res=pgDatabaseHelper.getAllData();
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Name : " +res.getString(1).toString()+"\n");
            buffer.append("Email : " +res.getString(2).toString()+"\n");
            buffer.append("Comment : " +res.getString(3).toString()+"\n");
            buffer.append("Rating : " +res.getString(4).toString()+"\n");
            buffer.append("\n");
        }
        showMessage("Data", buffer.toString());

    }*/


    public void openProperty(View view)
    {
        List<PgContact> pgContactList=new ArrayList<>();
        pgContactList=pgDatabaseHelper.getAllPg();
        if(pgContactList==null){
            showMessage("Error","Nothing Found");
        }else {
            Intent intent=new Intent(this,PgList.class);
        startActivity(intent);}
    }
    public void openFeedback(View v)
    {
        Cursor res=feedbackDatabaseHelper.getAllData();
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Name : " +res.getString(1).toString()+"\n");
            buffer.append("Rent : " +res.getString(2).toString()+"\n");
            buffer.append("Type : " +res.getString(3).toString()+"\n");
            buffer.append("Rating : " +res.getString(4).toString()+"\n");
//            buffer.append("Location : " +res.getString(5).toString()+"\n");
            buffer.append("\n");
        }
        showMessage("Data", buffer.toString());
    }


    public void openBooking(View v){

        Cursor res=bookingDatabase.getAllData();
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("PgName : " +res.getString(0).toString()+"\n");
            buffer.append("Name : " +res.getString(1).toString()+"\n");
            buffer.append("Email : " +res.getString(2).toString()+"\n");
            buffer.append("Phone : " +res.getString(3).toString()+"\n");
            buffer.append("Amount : " +res.getString(4).toString()+"\n");
//            buffer.append("Location : " +res.getString(5).toString()+"\n");
            buffer.append("\n");
        }
        showMessage("Data", buffer.toString());


    }



    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable (true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),AdminSection.class);
        startActivity(i);
    }







}
