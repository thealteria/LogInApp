package com.thealteria.loginapp;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Register extends AppCompatActivity {

    EditText name, user, pass, cnfrmpass;
    Button signin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);

        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        cnfrmpass = (EditText) findViewById(R.id.cnfrmpassword);
        user = (EditText) findViewById(R.id.user);
        signin = (Button) findViewById(R.id.signin);
    }

    public void signinBtn(View view) {

        if(name.getText().toString().equals("")||
        user.getText().toString().equals("")||
                pass.getText().toString().equals("")||cnfrmpass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }
        // check if both password matches
        if(!pass.getText().toString().equals(cnfrmpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Confirm Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            boolean isInserted = dbHelper.addUser(name.getText().toString(),
                    user.getText().toString(), pass.getText().toString(),
                    cnfrmpass.getText().toString());

            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);

            if (isInserted)
                Toast.makeText(Register.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(Register.this, "Data not Inserted", Toast.LENGTH_LONG).show();

        }

    }
}
