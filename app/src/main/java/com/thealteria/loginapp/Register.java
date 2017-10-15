package com.thealteria.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Register extends AppCompatActivity {

    TextInputEditText name, user1, pass, cnfrmpass;
    Button signin;
    Cursor cursor;
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);

        name = (TextInputEditText) findViewById(R.id.name);
        pass = (TextInputEditText) findViewById(R.id.password);
        cnfrmpass = (TextInputEditText) findViewById(R.id.cnfrmpassword);
        user1 = (TextInputEditText) findViewById(R.id.user);
        signin = (Button) findViewById(R.id.signin);
    }

    public void signinBtn(View view) {

       if(name.getText().toString().equals("")||
        user1.getText().toString().equals("")||
                pass.getText().toString().equals("")||cnfrmpass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }

        // check if both password matches
        if(!pass.getText().toString().equals(cnfrmpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }

        else {
            dbHelper.addUser(name.getText().toString(),
                    user1.getText().toString(), pass.getText().toString(),
                    cnfrmpass.getText().toString());

            Toast.makeText(Register.this, "Data Inserted", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        }

    }
}
