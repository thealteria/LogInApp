package com.thealteria.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import static android.R.attr.id;
import static android.content.ContentValues.TAG;

/**
 * Created by Aman on 9/18/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "singin.db";

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CNFRMPASS = "cnfrmpassword";

    public  SQLiteDatabase db ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    private String CREATE_TABLE = " CREATE " + USER_TABLE +
            "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT. " + COLUMN_NAME + "TEXT," + COLUMN_USERNAME + "TEXT," +
            COLUMN_PASSWORD + "TEXT," + COLUMN_CNFRMPASS + "TEXT" +")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /* Storing User details*/

    public void addUser(String name, String username, String password, String cnfrmpassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CNFRMPASS, cnfrmpassword);

        db.insert(USER_TABLE, null, values);
        db.close();
        }


    public void retrieveData()
    {
        String[] columns = {

                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_USERNAME,
                DBHelper.COLUMN_PASSWORD,
                DBHelper.COLUMN_CNFRMPASS
        };

        Cursor cursor = db.query(DBHelper.USER_TABLE, columns, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                String username = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_USERNAME));
                String pass = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PASSWORD));
                String cnfrmpass = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CNFRMPASS));
            }
        }
    }

   public Cursor getData(){
       SQLiteDatabase db = this.getWritableDatabase();
       return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
   }
}
