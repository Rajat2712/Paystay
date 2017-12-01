package DatabaseHelpers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajat on 4/27/2017.
 */

public class BookingDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="bookingData";
    private static final String TABLE_NAME="bookingInformation";
    private static final String COLUMN_PGNAME="pgName";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_AMOUNT= "amount";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_Phone="phone";



    public BookingDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public BookingDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BookingDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS  " + TABLE_NAME + "("
                 + COLUMN_PGNAME + " TEXT," + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT," + COLUMN_Phone +" TEXT," + COLUMN_AMOUNT +" TEXT"  + ")";

        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);


    }




    public void InsertBooking(BookingContact bookingContact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PGNAME, bookingContact.getPgName());
        values.put(COLUMN_NAME, bookingContact.getName());
        values.put(COLUMN_EMAIL, bookingContact.getEmail());
        values.put(COLUMN_Phone, bookingContact.getPhone());
        values.put(COLUMN_AMOUNT, bookingContact.getAmount());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public List<BookingContact> getAllBooking() {
        List<BookingContact> bookingContactList = new ArrayList<BookingContact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                BookingContact bookingContact = new BookingContact();
                //pgContact.setID(Integer.parseInt(cursor.getString(0)));
                bookingContact.setPgName(cursor.getString(0));
                bookingContact.setName(cursor.getString(1));
                bookingContact.setEmail(cursor.getString(2));
                bookingContact.setPhone(cursor.getString(3));
                bookingContact.setAmount(cursor.getString(4));
                bookingContactList.add(bookingContact);
            } while (cursor.moveToNext());
        }else {bookingContactList=null; }
        cursor.close();
        db.close();


        return bookingContactList;
    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor=db.rawQuery(selectQuery, null);
        return cursor;

    }















}
