package DatabaseHelpers;

/**
 * Created by Preetika on 22-04-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="pgInfo.db";
    private static final String TABLE_NAME="pgUsers";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PHNO="phno";
    private static final String COLUMN_PASS="pass";
    private static String a;
    static String b="not found";
    SQLiteDatabase db;
    public DatabaseHelper(Context context)
    {

        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE pgUsers (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL, phno TEXT NOT NULL, pass TEXT NOT NULL);");
        this.db=db;

    }
    public void insertContact(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String query="select * from pgUsers";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PHNO,c.getPhno());
        values.put(COLUMN_PASS, c.getPass());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    public String searchPass(String email)
    {
        //Toast.makeText(DatabaseHelper.this, "Searched", Toast.LENGTH_SHORT).show();
        db=this.getReadableDatabase();
        String query="select email,pass from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(email))
                {
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext()) ;
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}

