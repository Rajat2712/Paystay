package com.example.rajat.paystay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;

public class FirstScreen extends AppCompatActivity {
    android.support.v7.widget.ShareActionProvider shareActionProvider;

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
        //sets auto flipping

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_name, menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        shareActionProvider= (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Download our app on \n googleplay.PayStay");
        shareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.Admin:
                // search action

                Intent intent = new Intent(this, AdminLogin.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent1=new Intent(this,Login.class);
                startActivity(intent1);
                Toast.makeText(FirstScreen.this, "Logged Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Exit:
                finish();

        }

        return super.onOptionsItemSelected(item);

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

    public void openLoginPage(View view) {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }

    public void openAboutUs(View view) {
        Intent intent = new Intent(this,AboutUs.class);
        startActivity(intent);
    }

    public void openFeedback(View view) {
        Intent intent=new Intent(this,FeedbackForm.class);
        startActivity(intent);
    }
    public void OpenSearchPage(View v)
    {

        PgDatabaseHelper pgDatabaseHelper=new PgDatabaseHelper(this);
        List<PgContact> pgContactList=new ArrayList<>();
        pgContactList=pgDatabaseHelper.getAllPg();
        if(pgContactList==null){
            showMessage("Error","Nothing Found");
        }else {
            Intent intent=new Intent(this,PgListUser.class);
            startActivity(intent);}


    }


    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable (true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void openProperty(View view)
    {
        Intent intent=new Intent(this,AddPg.class);
        startActivity(intent);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
        startActivity(i);
    }











}




















