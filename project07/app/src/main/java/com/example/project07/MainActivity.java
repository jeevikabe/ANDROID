package com.example.project07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {
    Button btn1,btn2;
    EditText username,email;
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        btn1 = (Button) findViewById(R.id.btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                if ((!TextUtils. isEmpty(user))&&(!TextUtils. isEmpty(mail))) {

                    if (validate(mail)) {
                        Bundle bd = new Bundle();
                        bd.putString("username", user);
                        Intent it = new Intent(MainActivity.this, MovieSelect.class);
                        it.putExtra("username", bd);
                        startActivity(it);
                    } else Toast.makeText(getBaseContext(), "invalid email", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getBaseContext(), "fill the details", Toast.LENGTH_LONG).show();
            }

        });
        btn2 = (Button) findViewById(R.id.anonymous_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bd = new Bundle();
                bd.putString("username"," ");
                Intent it = new Intent(MainActivity.this,MovieSelect.class);
                it.putExtra("username",bd);
                startActivity(it);
            }
        });
    }

    boolean validate(String mail){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.matches();
    }


}