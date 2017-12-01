package com.example.rajat.paystay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import DatabaseHelpers.BookingContact;
import DatabaseHelpers.BookingDatabase;

public class Booking extends AppCompatActivity {

    BookingDatabase bookingDatabase;
    BookingContact bookingContact;
    String pgName,uname,uemail,uphone,uamount;
    EditText name,email,phone,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        name=(EditText)findViewById(R.id.textView5);
        email=(EditText)findViewById(R.id.textView8);
        phone=(EditText)findViewById(R.id.textView6);
        amount=(EditText)findViewById(R.id.textView9);

        bookingDatabase=new BookingDatabase(this);

        Intent intent=getIntent();
        pgName=intent.getExtras().getString("name");
        ImageView imageView;
        imageView=(ImageView)findViewById(R.id.booking);
        Animation animation1= AnimationUtils.loadAnimation(this, R.anim.bounce);
        imageView.startAnimation(animation1);
    }
    public void Done(View view)
    {
        uname=name.getText().toString();
        uemail=email.getText().toString();
       uphone=phone.getText().toString();
        uamount=amount.getText().toString();

        if(uname.isEmpty()||uemail.isEmpty()||(uphone.isEmpty())||uamount.isEmpty()){

            Toast.makeText(getApplicationContext(),"Field cannot be empty !", Toast.LENGTH_SHORT).show();

        }
        else {

            bookingContact = new BookingContact(pgName, uname, uemail, uphone, uamount);

            bookingDatabase.InsertBooking(bookingContact);


            Toast.makeText(getApplicationContext(), "Done Successfully !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FirstScreen.class);
            startActivity(intent);

        }

    }
    public void Cancel(View view)
    {
        Intent intent=new Intent(this,PgListUser.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),PgListUser.class);
        startActivity(i);
    }










}
