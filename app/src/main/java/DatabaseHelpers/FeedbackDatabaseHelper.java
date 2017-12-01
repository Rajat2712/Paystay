package DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;


import java.util.Random;

/**
 * Created by Preetika on 27-04-2016.
 */
public class FeedbackDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="feedback.db";
    private static final String TABLE_NAME="feedback";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_COMMENT="comment";
    private static final String COLUMN_RATING="rating";
    private static String a;
    static String b="not found";
    SQLiteDatabase db;
    public FeedbackDatabaseHelper(Context context)
    {

        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE feedback (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL,comment TEXT NOT NULL,rating TEXT NOT NULL);");
        this.db=db;

    }
    public void insertFeedback(FeedbackContact c)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String query="select * from feedback";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_COMMENT,c.getComment());
        values.put(COLUMN_RATING, c.getRating());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    //Admin can view the feedback
    public Cursor getAllData()
    {
        db=this.getWritableDatabase();
        String query="select * from feedback";
        Cursor cursor=db.rawQuery(query, null);
        return cursor;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        this.onCreate(db);

    }
}



   /* private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="pgInfo.db";
    private static final String TABLE_NAME="feedbackInformation";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_COMMENT="comment";
    private static final String COLUMN_RATING="rating";
Context context;
    SQLiteDatabase db;

       public FeedbackDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE feedbackInformation (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL, comment TEXT NOT NULL, rating TEXT NOT NULL);");
        this.db = db;
    }

 @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE feedbackInformation (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL, comment TEXT NOT NULL, rating TEXT NOT NULL);");
        this.db=db;
        insertFeedbackData("Preetika", "ppreetikaa@gmail.com", "good", "5", db);
        insertFeedbackData("Suruchi", "Suruchi0808@gmail.com", "Excellent", "4.5", db);
          }


     public void insertFeedbackData(String name, String email,String comment, String rating, SQLiteDatabase db)
    {
        ContentValues tableValues=new ContentValues();
        tableValues.put("name", name);
        tableValues.put("email", email);
        tableValues.put("comment", comment);
        tableValues.put("rating", rating);
     //   INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)] VALUES (value1, value2, value3,...valueN);
//        String query="INSERT INTO "+TABLE_NAME+" ("+COLUMN_NAME+ " , "+COLUMN_EMAIL+" , "+COLUMN_COMMENT+" , "+COLUMN_RATING+" ) VALUES("
  //              +name+ " , "+ email+" , "+comment+" , "+rating+ " ) ;";
    //    Cursor c=db.rawQuery(query,null);
          db.insert("feedbackInformation", null, tableValues);
    }

    //Admin can view the feedback
    public Cursor getAllData()
    {
        db=this.getWritableDatabase();
        String query="select * from feedbackInformation";
        Cursor cursor=db.rawQuery(query, null);
        return cursor;

    }
    public void insertFeedback(FeedbackContact c)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
//        String query="select * from feedbackInformation";
  //      Cursor cursor1=db.rawQuery(query,null);
    //    int count=cursor1.getCount();
        Random R=new Random();
        int r=R.nextInt(255);
        values.put(COLUMN_ID,r);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_COMMENT,c.getComment());
       values.put(COLUMN_RATING, "5");
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}*/