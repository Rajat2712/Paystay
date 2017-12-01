package com.example.rajat.paystay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DatabaseHelpers.PgDatabaseHelper;

public class Search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner,spinner1, spinner2;
   RadioButton opMale,opFemale, opBoth;
    Button btSearch;
    RadioGroup radioGroup;
    SQLiteDatabase db;
    public String location, type, type1;
    public String rent, value1,value2;

    PgDatabaseHelper pgDatabaseHelper=new PgDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       /* opMale= (RadioButton) findViewById(R.id.optBoys);
        opFemale= (RadioButton) findViewById(R.id.optGirls);
        opBoth= (RadioButton) findViewById(R.id.optBoth);
        btSearch=(Button)findViewById(R.id.btnSearch);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        radioGroup=(RadioGroup)findViewById(R.id.radio);
        */

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList <String>();
        categories.add("Chandigarh");
        categories.add("Panchkula");
        List<String> catRate=new ArrayList<String>();
        catRate.add("3000-4000");
        catRate.add("4000-5000");
        catRate.add("5000-6000");
        catRate.add("6000-7000");
        List<String> catType=new ArrayList<String>();
        catType.add("Boys");
        catType.add("Girls");
        catType.add("Both");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,catRate);
        ArrayAdapter<String> dataAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,catType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter1);
        spinner2.setAdapter(dataAdapter2);
        Toast.makeText(Search.this, "On Create Done", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        location = parent.getItemAtPosition(position).toString();
        rent=parent.getItemAtPosition(position).toString();
        type1=parent.getItemAtPosition(position).toString();

        if(rent=="3,000-4,000")
        {
            value1="3000";
            value2="4000";
        }
        if(rent=="4,000-5,000")
        {
            value1="4000";
            value2="5000";
        }
        if(rent=="5,000-6,000")
        {
            value1="5000";
            value2="6000";
        }
        if(rent=="6,000-7,000")
        {
            value1="6000";
            value2="7000";
        }
//        String cat= String.valueOf(radioGroup.getCheckedRadioButtonId());

        if(type1.equals(opBoth))
        {
            type="Both";
        }
        if(type1.equals(opMale))
        {
            type="Boys";
        }
        if(type1.equals(opFemale))
        {
            type="Girls";
        }
    }

  /*  public void searchNow(View v)
    {
        PgDatabaseHelper pg=new PgDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = pg.getReadableDatabase();
        String loc=pg.searchPg(location,value1,value2,type);
        Cursor res=pgDatabaseHelper.getAllData();
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {

            buffer.append("Pg Name : " +res.getString(1).toString()+"\n");
            buffer.append("Rent : " +res.getString(2).toString()+"\n");
            buffer.append("Type : " +res.getString(3).toString()+"\n");
            buffer.append("Address : " +res.getString(4).toString()+"\n");
            buffer.append("Location : " +res.getString(5).toString()+"\n");
            buffer.append("\n");
        }
        showMessage("Data",buffer.toString());


    }*/
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
/*
    public void ViewAll(View v)
    {
        //  Toast.makeText(Search.this, "Searching Start", Toast.LENGTH_SHORT).show();
        Cursor res=pgDatabaseHelper.getAllData();
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {

            buffer.append("Pg Name : " +res.getString(1).toString()+"\n");
            buffer.append("Rent : " +res.getString(2).toString()+"\n");
            buffer.append("Type : " +res.getString(3).toString()+"\n");
            buffer.append("Address : " +res.getString(4).toString()+"\n");
            buffer.append("Location : " +res.getString(5).toString()+"\n");
            buffer.append("\n");
        }
        showMessage("Data",buffer.toString());

    }
*/

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable (true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
