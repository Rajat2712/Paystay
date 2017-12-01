package com.example.rajat.paystay;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;

/**
 * Created by Rajat on 11/1/2016.
 */
public class PgInfo extends AppCompatActivity {

    int id;
    PgContact pgContact;
    PgDatabaseHelper pgDatabaseHelper;
    TextView name,city,address,phone,type,rent;
    ImageView photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pg_info);
        Intent i=getIntent();

        id=i.getExtras().getInt("id");
        /*if(i!=null){
            id=i.getExtras().getInt("id");}*/
        name=(TextView)findViewById(R.id.textView64);
        city=(TextView)findViewById(R.id.textView66);
        address=(TextView)findViewById(R.id.textView68);
        phone=(TextView)findViewById(R.id.textView70);
        type=(TextView)findViewById(R.id.textView72);
        rent=(TextView)findViewById(R.id.textView74);
        photo=(ImageView)findViewById(R.id.imageView11);

        pgDatabaseHelper=new PgDatabaseHelper(this);
        pgContact=pgDatabaseHelper.getPg(id);


        if(pgContact!=null) {
            name.setText(pgContact.getPgName());
            city.setText(pgContact.getpgLocation());
            address.setText(pgContact.getPgAddress());
            phone.setText(pgContact.getPgPhone());
            type.setText(pgContact.getpgType());
            rent.setText(pgContact.getpgRent());
            if(pgContact.getPhoto()!=null)
            photo.setImageBitmap(BitmapFactory.decodeByteArray(pgContact.getPhoto(), 0, pgContact.getPhoto().length));
        }

    }

    public void Edit(View view){

        Intent a=new Intent(getApplicationContext(),EditPgInfo.class);
        a.putExtra("id",id);
        startActivity(a);


    }


    public void Delete(View view){

        pgDatabaseHelper.deletePg(id);
        Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
        Intent d=new Intent(getApplicationContext(),PgList.class);
        startActivity(d);


    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),PgList.class);
        startActivity(i);
    }




}
