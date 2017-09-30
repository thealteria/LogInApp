package com.thealteria.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CNFRMPASS = "cnfrmpassword";

    public  SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                "(" + COLUMN_NAME + " TEXT," + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT," + COLUMN_CNFRMPASS + " TEXT" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /* Storing User details*/

    public boolean addUser(String name, String username, String password, String cnfrmpassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CNFRMPASS, cnfrmpassword);

        long result = db.insert(USER_TABLE, null, values);
        db.close();

        return result != -1;
    }

    public String getSingleEntry(String userName)
    {
        Cursor cursor= db.query(USER_TABLE, null, "username=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST...!!!";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
        cursor.close();
        return "Password="+password;

    }

   /* public Cursor getUser(String username, String password) {

        String selectQuery = "SELECT * FROM  " + USER_TABLE + " WHERE " +
                COLUMN_USERNAME + " = " + username + " " + " AND "
                + COLUMN_PASSWORD + " = " + password + " ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }*/
}
