package com.example.rajat.paystay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
    public void genOTP(View v)
    {
        /*   int rNo;
        Random random=new Random();
        rNo=random.nextInt(3000);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("tel:+919056861950"));
        startActivity(intent);*/

    }
}
