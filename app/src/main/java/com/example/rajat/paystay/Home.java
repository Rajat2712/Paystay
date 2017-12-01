package com.example.rajat.paystay;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Rajat on 10/21/2016.
 */
public class Home extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }



    public void admin(View view){
        Intent intent=new Intent(this,AdminLogin.class);
        startActivity(intent);

    }

    public void user(View view){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);

    }

}
