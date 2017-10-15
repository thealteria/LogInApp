package com.thealteria.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    TextInputEditText username1, pass;
    Button login;
    Cursor cursor;
    TextView attempt, atpCnt1;
    CheckBox show;
    int atpCnt = 5;
    int k = 0;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        username1 = (TextInputEditText) findViewById(R.id.luser);
        pass = (TextInputEditText) findViewById(R.id.lpass);
        show = (CheckBox) findViewById(R.id.showPass);
        attempt = (TextView)findViewById(R.id.attempt);
        atpCnt1 = (TextView)findViewById(R.id.attemptCount);

        atpCnt1.setText(Integer.toString(atpCnt));

        login = (Button)findViewById(R.id.login);
        showPass();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursor = db.rawQuery("SELECT * FROM " + DBHelper.USER_TABLE + " WHERE "
                                + DBHelper.COLUMN_USERNAME + " =? AND " + DBHelper.COLUMN_PASSWORD + " =?",
                        new String[]{username1.getText().toString(), pass.getText().toString()});

                if(username1.getText().toString().equals("")||
                        pass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Username and Password can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        Toast.makeText(Login.this, "Login ho gya bc!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, LoginMainPage.class);
                        startActivity(intent);
                    } else {
                        atpCnt--;
                        Toast.makeText(Login.this, "Login ni hua bc!",
                                Toast.LENGTH_SHORT).show();
                        atpCnt1.setText(Integer.toString(atpCnt));

                        if (atpCnt == 0) {
                            login.setEnabled(false);
                            Toast.makeText(Login.this, "Restart the app and try to login again",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }

    public void showPass(){
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }


    public void onBackPressed()
    {
        k++;
        if(k == 1)
        {
            Toast.makeText(Login.this, "Press again to to go back.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            finish();
        }
    }
}
