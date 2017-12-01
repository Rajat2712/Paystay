
package com.example.rajat.paystay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DatabaseHelpers.Contact;
import DatabaseHelpers.DatabaseHelper;


public class UserRegister extends AppCompatActivity {
   DatabaseHelper helper=new DatabaseHelper(this);
    EditText etname;
    EditText etmail;
    EditText etphoneNo;
    EditText etpassword;
    EditText etcpassword;
    Button bregister;
    CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        etname=(EditText)findViewById(R.id.editText3);
        etmail=(EditText)findViewById(R.id.editText4);
        etphoneNo=(EditText)findViewById(R.id.editText5);
        etpassword=(EditText)findViewById(R.id.editText6);
        etcpassword=(EditText)findViewById(R.id.editText7);
        bregister=(Button)findViewById(R.id.button4);

        box=(CheckBox)findViewById(R.id.checkbox);


    }
    public void SignUp(View v) {
        final String name = etname.getText().toString();
        boolean result1, result2, result3,  result4,result5=false , result6 =false;
        boolean ch = true;

      if((name.length() == 0)||(!name.matches("[A-Za-z ]+")))
        {
           // etname.requestFocus();
           etname.setError("Invalid Name");
            result1=false;
        }
        else
        {
            result1=true;
        }
      /*  if ( (name.length() == 0)||(isValidName(name)))
        {
            etname.setError("Invalid Name");
            Toast.makeText(UserRegister.this, "if Block", Toast.LENGTH_SHORT).show();
            result1 = false;
        }
        else {
            result1=true;
            Toast.makeText(UserRegister.this, "Else Block", Toast.LENGTH_SHORT).show();
        }*/

        final String email = etmail.getText().toString();

        if (!isValidEmail(email)) {
            etmail.setError("Invalid Email");
            result2 = false;
        }
        else {
            result2=true;
        }
        final String phno = etphoneNo.getText().toString();
       if (phno.length() == 0 || phno.length()!=10)
        {
            etphoneNo.setError("Invalid no");
            result3 = false;
        }
       else {
           result3=true;
       }

        final String pass = etpassword.getText().toString();
        if (!isValidPassword(pass)) {
            etpassword.setError("Invalid Password. Password length must be between 6 and 15");
            result4 = false;
        }
        else {
            result4=true;
        }

        final String confrm = etcpassword.getText().toString();
        if (pass.equals(confrm)) {
                result5=true;
        } else {
            etpassword.setError("Not match please try again ");
            result5 = false;

        }

        if(false==checkclick(box))
        {
            box.setError("check the check box");

        }
        else
        {
            result6=true;
        }
        ch=checkclick(box);
        if(ch==false)
        {
            box.setError("select terms and conditions");
        }

        if (result1 == true && result2==true && result3 == true && result4 == true && result5 == true && result6 == true ) /*`&& ch==true)*/ {
            Contact c = new Contact();
            c.setName(name);
            c.setEmail(email);
            c.setPass(pass);
            c.setPhno(phno);
            helper.insertContact(c);

            Intent intent = new Intent(UserRegister.this, FirstScreen.class);
            startActivity(intent);
            //setVisibility(true);

        }
    }
    public boolean checkclick(View view)
    {
        return box.isChecked();
    }
 /*   private boolean isValidName(String name)
    {
        String NAME_PATTERN = "[A-Za-z ]+";

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }*/

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6 && pass.length()<15) {
            return true;
        }
        return false;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }


















}
