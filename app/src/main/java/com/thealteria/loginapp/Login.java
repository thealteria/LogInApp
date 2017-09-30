package com.thealteria.loginapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    EditText username1, pass;
    Button login;
    TextView attempt, atpCnt1;
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

        username1 = (EditText)findViewById(R.id.luser);
        pass = (EditText)findViewById(R.id.lpass);
        attempt = (TextView)findViewById(R.id.attempt);
        atpCnt1 = (TextView)findViewById(R.id.attemptCount);

        atpCnt1.setText(Integer.toString(atpCnt));

        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String storedPassword = dbHelper.getSingleEntry(username1.getText().toString());

                if(pass.equals(storedPassword)){

                    Toast.makeText(Login.this, "Login ho gya bc!",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, LoginMainPage.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(Login.this, "Login ni hua bc!",
                            Toast.LENGTH_LONG).show();
                    atpCnt--;
                    attempt.setText(Integer.toString(atpCnt));;

                    if(atpCnt==0){
                        login.setEnabled(false);
                    }
                }
            }
        });

    }

    public void onBackPressed()
    {
        k++;
        if(k == 1)
        {
            Toast.makeText(Login.this, "Press again to Exit.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            finish();
        }
    }
}
