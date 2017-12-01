package com.example.rajat.paystay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText user, password;
    public static final String MyAdminPREFERENCES = "MyAdminPrefs";
    public static final String AdminName = "AdminnameKey";
    public static final String AdminEmail = "AdminemailKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);
        sharedpreferences = getSharedPreferences(MyAdminPREFERENCES, Context.MODE_PRIVATE);
        getSupportActionBar().setTitle("PayStay Administrator");

    }
    public void loginToAdmin(View v)
    {
        if(user.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
        {
            Intent intent=new Intent(this,AdminSection.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(AdminLogin.this, "Incorrect Username Or Password", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
    }






}
