
package com.example.rajat.paystay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHelpers.DatabaseHelper;


public class Login extends AppCompatActivity {
    DatabaseHelper databaseHelper=new DatabaseHelper(this);
     EditText etuser;
     EditText etpassword;
     Button blogin;
    TextView tvGreet;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("PayStay");
        etuser = (EditText) findViewById(R.id.user);
        etpassword = (EditText) findViewById(R.id.password);
        blogin = (Button) findViewById(R.id.login);
     //   Intent intent=getIntent();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        tvGreet=(TextView)findViewById(R.id.signUpGreet);

    }
    public  void loginToMain(View v)
    {
       // Toast.makeText(Login.this, "Clicked", Toast.LENGTH_SHORT).show();
        String email = etuser.getText().toString();
        String pass = etpassword.getText().toString();
        String password = databaseHelper.searchPass(email);
       // Toast.makeText(Login.this, "search passed", Toast.LENGTH_SHORT).show();

            //Toast.makeText(getApplicationContext(),password,Toast.LENGTH_LONG).show();
            if (password.equals(pass)) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, email);
                editor.putString(Email, pass);
                editor.commit();
                Intent intent = new Intent(Login.this, FirstScreen.class);
                startActivity(intent);

            } else {
                Toast.makeText(Login.this, "Username Password not match", Toast.LENGTH_SHORT).show();
            }
    }
    public void regUser(View v)
    {
       // Toast.makeText(Login.this, "Redirecting to User Registration ", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,UserRegister.class);
        startActivity(intent);
    }
    /*
    public void forgotPwd(View v)
    {
        Intent i=new Intent(this,ForgotPassword.class);
        startActivity(i);
    }
   */






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
    }










}
