package com.example.rajat.paystay;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;

/**
 * Created by Rajat on 11/1/2016.
 */
public class EditPgInfo extends AppCompatActivity{

    private static int RESULT_LOAD_IMAGE = 1;
    byte[] imageInByte;
    EditText et, et1, et2, et3;
    PgDatabaseHelper pgDatabaseHelper=new PgDatabaseHelper(this);
    TextView photo;
    String city;
    Spinner spinner , spinner1;
    PgContact pg;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_pg_info);
        Intent a=getIntent();
        p=a.getExtras().getInt("id");
        et=(EditText)findViewById(R.id.editText);
        et1=(EditText)findViewById(R.id.editText2);
        et2=(EditText)findViewById(R.id.editText3);
        et3=(EditText)findViewById(R.id.editText4);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        photo=(TextView)findViewById(R.id.textView2);



        pg=pgDatabaseHelper.getPg(p);
        if(pg!=null) {
            et.setText(pg.getPgName());
            et1.setText(pg.getPgAddress());
            et2.setText(pg.getPgPhone());
            et3.setText(pg.getpgRent());
            imageInByte=pg.getPhoto();

        }




        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });

    }

    public void Post(View view)
    {
        final String name=et.getText().toString();
        final String city= String.valueOf(spinner.getSelectedItem().toString());
        final String address=et1.getText().toString();
        final String phone=et2.getText().toString();
        final String type=String.valueOf(spinner1.getSelectedItem().toString());
        final String price=et3.getText().toString();



        PgContact pgContact = new PgContact();
        pgContact.setPgName(name);
        pgContact.setPgLocation(city);
        pgContact.setPgAddress(address);
        pgContact.setPgPhone(phone);
        pgContact.setPgType(type);
        pgContact.setPhoto(imageInByte);
        pgContact.setPgRent(price);
        pgDatabaseHelper.updatePg(pgContact, p);
        Toast.makeText(this, "Feedback Submitted Successfully. Thanks for your valuable support", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),PgInfo.class);
        i.putExtra("id", p);
        startActivity(i);

    }


    public void Cancel(View view){

        Intent i=new Intent(getApplicationContext(),PgInfo.class);
        i.putExtra("id", p);
        startActivity(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap mBitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageInByte = stream.toByteArray();
            photo.setText(picturePath);

        }


    }










}
