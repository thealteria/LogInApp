package com.thealteria.loginapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginMainPage extends AppCompatActivity {

    Button view;
    DBHelper dbHelper;
    TextView show;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main_page);
        dbHelper = new DBHelper(this);

        view = (Button)findViewById(R.id.view);
        show = (TextView)findViewById(R.id.showAll);
        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbHelper.getData();
                StringBuilder stringB = new StringBuilder();
                if(res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringB.append("Name: "+res.getString(0)+"\n");
                        stringB.append("Username: "+res.getString(1)+"\n");
                        stringB.append("Password: "+res.getString(2)+"\n");
                    }
                    show.setText(stringB.toString());
                    Toast.makeText(LoginMainPage.this, "Data showed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginMainPage.this, "Data N/A", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
