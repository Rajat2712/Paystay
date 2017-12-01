package com.example.rajat.paystay;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;
import DatabaseHelpers.Utils;

/**
 * Created by Rajat on 11/1/2016.
 */
public class AddPg extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    byte[] imageInByte;
    EditText et, et1, et2, et3;
    PgDatabaseHelper pgDatabaseHelper=new PgDatabaseHelper(this);
    TextView photo;
    //String city;
    Spinner spinner , spinner1;


    //private static final int SELECT_PICTURE = 100;
    //rivate static final String TAG = "MainActivity";
   // byte[] inputData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ur_ad);
        et=(EditText)findViewById(R.id.editText);
        et1=(EditText)findViewById(R.id.editText2);
        et2=(EditText)findViewById(R.id.editText3);
        et3=(EditText)findViewById(R.id.editText4);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        photo=(TextView)findViewById(R.id.textView2);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

                //openImageChooser();


            }
        });

    }


    /*void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }
*/



/*

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                        //showMessage("Image Saved in Database...");
                        //imgView.setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           */
/* if (loadImageFromDB()) {
                                //showMessage("Image Loaded from Database...");
                            }*//*

                        }
                    }, 3000);
                }

            }
        }
    }
*/




    /*Boolean saveImageInDB(Uri selectedImageUri) {

        try {
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            inputData = Utils.getBytes(iStream);
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            return false;
        }

    }
*/
  /*  Boolean loadImageFromDB() {
        try {
            byte[] bytes = dbHelper.retreiveImageFromDB();
            dbHelper.close();
            // Show Image from DB in ImageView
            imgView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }*/



























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
        //pgContact.setPhoto(inputData);
        pgContact.setPgRent(price);
        pgDatabaseHelper.InsertPg(pgContact);
        Toast.makeText(this, "Pg Added", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
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
            Bitmap mBitmap = decode_File(picturePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageInByte = stream.toByteArray();
            photo.setText(picturePath);




            /*try {
                InputStream iStream = getContentResolver().openInputStream(selectedImage);
                inputData = Utils.getBytes(iStream);
            } catch (IOException ioe) {
                Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());

            }
*/

        }


    }


    private Bitmap decode_File(String f) {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        //BitmapFactory.decodeStream(new FileInputStream(f), null, o);
        BitmapFactory.decodeFile(f,o);

        // The new size we want to scale to
        final int REQUIRED_SIZE=120;

        // Find the correct scale value. It should be the power of 2.
        int scale = 1;
        while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE) {
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        Toast.makeText(getApplicationContext(),"Scale:-"+scale,Toast.LENGTH_SHORT).show();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeFile(f,o2);

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),FirstScreen.class);
        startActivity(i);
    }





}
