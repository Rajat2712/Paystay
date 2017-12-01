package DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Preetika on 28-04-2016.
 */
public class PgDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=3;
    private static final String DATABASE_NAME="pgData";
    private static final String TABLE_NAME="pgInformation";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="pgName";
    private static final String COLUMN_RENT= "rent";
    private static final String COLUMN_TYPE="type";
    private static final String COLUMN_ADDRESS="address";
    private static final String COLUMN_Phone="phone";
    private static final String COLUMN_LOCATION="location";
    private static final String COLUMN_PHOTO="photo";




    public PgDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PgDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PgDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS  " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_LOCATION + " TEXT,"
                + COLUMN_ADDRESS + " TEXT," + COLUMN_Phone +" TEXT," + COLUMN_TYPE +" TEXT,"  + COLUMN_PHOTO +" BLOB," + COLUMN_RENT + " TEXT"  + ")";

        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void InsertPg(PgContact pgContact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pgContact.getPgName());
        values.put(COLUMN_LOCATION, pgContact.getpgLocation());
        values.put(COLUMN_ADDRESS, pgContact.getPgAddress());
        values.put(COLUMN_Phone, pgContact.getPgPhone());
        values.put(COLUMN_TYPE, pgContact.getpgType());
        values.put(COLUMN_PHOTO, pgContact.getPhoto());
        values.put(COLUMN_RENT, pgContact.getpgRent());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public List<PgContact> getAllPg() {
        List<PgContact> pgContactArrayList = new ArrayList<PgContact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                PgContact pgContact = new PgContact();
                //pgContact.setID(Integer.parseInt(cursor.getString(0)));
                pgContact.setID(cursor.getInt(0));
                pgContact.setPgName(cursor.getString(1));
                pgContact.setPgLocation(cursor.getString(2));
                pgContact.setPgAddress(cursor.getString(3));
                pgContact.setPgPhone(cursor.getString(4));
                pgContact.setPgType(cursor.getString(5));
                pgContact.setPhoto(cursor.getBlob(6));
                pgContact.setPgRent(cursor.getString(7));
                pgContactArrayList.add(pgContact);
            } while (cursor.moveToNext());
        }else {pgContactArrayList=null; }
        cursor.close();
        db.close();


        return pgContactArrayList;
    }




    public int getPgCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }



    public PgContact getPg(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true,TABLE_NAME, new String[]{ COLUMN_ID,
                        COLUMN_NAME,COLUMN_LOCATION,COLUMN_ADDRESS,COLUMN_Phone,COLUMN_TYPE,COLUMN_PHOTO,COLUMN_RENT }, COLUMN_ID + "=?",
                new String[]{ String.valueOf(id) }, null, null, null, null);



        if (cursor != null)
            cursor.moveToFirst();

        /*PgContact pgContact = new PgContact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getBlob(6),cursor.getString(7));
        */

        PgContact   pgContact = new PgContact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getBlob(6), cursor.getString(7));
        db.close();
        return pgContact;
    }


    public int updatePg(PgContact pgContact,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pgContact.getPgName());
        values.put(COLUMN_LOCATION, pgContact.getpgLocation());
        values.put(COLUMN_ADDRESS, pgContact.getPgAddress());
        values.put(COLUMN_Phone, pgContact.getPgPhone());
        values.put(COLUMN_TYPE, pgContact.getpgType());
        values.put(COLUMN_PHOTO, pgContact.getPhoto());
        values.put(COLUMN_RENT, pgContact.getpgRent());

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public void deletePg(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


}


