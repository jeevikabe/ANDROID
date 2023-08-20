package com.example.project07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MovieSelect extends AppCompatActivity {
    Button b1, b2;
    EditText movie_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_select);
        Bundle bd = getIntent().getBundleExtra("username");
        String name = bd.getString("username");
        Toast.makeText(getBaseContext(),"Hii  "+name,Toast.LENGTH_LONG).show();
        movie_name =  (EditText) findViewById(R.id.search_movie);
        String mov = movie_name.getText().toString();
        Button logoutButton = findViewById(R.id.btr);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieSelect.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

              b1 = (Button) findViewById(R.id.btn);
              b1.setOnClickListener(new View.OnClickListener() {

                  @Override
                  public void onClick(View v) {
                      String mov = movie_name.getText().toString();
                      if (!TextUtils.isEmpty(mov)) {
                          Intent it = new Intent(MovieSelect.this, review_page.class);
                          startActivity(it);
                      } else{
                          Toast.makeText(getApplicationContext(), "Enter Movie Name", Toast.LENGTH_SHORT).show();
                          }
                  }
              });
              b2 = (Button) findViewById(R.id.btn1);
              b2.setOnClickListener(new View.OnClickListener() {

                  @Override
                  public void onClick(View v) {
                      String mov = movie_name.getText().toString();
                      if (!TextUtils.isEmpty(mov)) {
                          Intent it = new Intent(MovieSelect.this, movie_details.class);
                          Bundle bd = new Bundle();
                          bd.putString("Movie_name", mov);
                          it.putExtra("data", bd);
                          startActivity(it);
                      } else
                          Toast.makeText(getApplicationContext(), "Enter Movie Name", Toast.LENGTH_SHORT).show();
                  }
              });




    }
}

