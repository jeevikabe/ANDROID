package com.example.project07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class review_page extends AppCompatActivity {
 Spinner spinner;
 Button btn;
 String[] genres ={"Action",
         "Comedy",
         "Drama",
         "Fantasy",
         "Horror",
         "Mystery",
         "Thriller"};
 RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(review_page.this, android.R.layout.simple_spinner_item,genres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button logoutButton = findViewById(R.id.btr);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(review_page.this, MovieSelect.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(review_page.this,value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn = (Button) findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Review Submitted",Toast.LENGTH_SHORT).show();

            }
        });
        rb = findViewById(R.id.star);
        String val = String.valueOf(rb.getRating());
        Toast.makeText(getApplicationContext(),val+"",Toast.LENGTH_SHORT).show();
    }
}



