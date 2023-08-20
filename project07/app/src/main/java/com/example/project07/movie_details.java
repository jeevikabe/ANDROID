package com.example.project07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class movie_details extends AppCompatActivity {
String name;
TextView txt1,txt2,txt3,txt4,txt5;
ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);



        Button logoutButton = findViewById(R.id.btr);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(movie_details.this, MovieSelect.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });
        txt1 = (TextView) findViewById(R.id.edit);
        txt2 = (TextView) findViewById(R.id.edit1);
        txt3 = (TextView) findViewById(R.id.edit2);
        txt4 = (TextView) findViewById(R.id.edit3);
        txt5 = (TextView) findViewById(R.id.edit4);

        Bundle bd = getIntent().getBundleExtra("data");
        name = bd.getString("Movie_name");
        fetchData(name);


    }

    private void fetchData(String name) {
        String url = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + name;


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "96291cd0camsh7d70ec6bc0d72dcp119977jsncd561d037a01")
                .addHeader("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(movie_details.this,"error",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if(response.isSuccessful()){
                String resp = response.body().toString();//response successfull,retrived as a string
                movie_details.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                             JSONObject obj = new JSONObject(response.body().string());
                          //  JSONObject obj = new JSONObject(resp);
                            String mov_name,type,rank,actor,url;

                            JSONArray arr = obj.getJSONArray("d");

                            JSONObject mov = arr.getJSONObject(0);
                            txt1.setText(mov.get("l").toString());
                           // txt1.setText(mov.getString("l"));
                            txt2.setText(mov.get("q").toString());
                            txt3.setText(mov.get("rank").toString());
                            txt4.setText(mov.get("s").toString());
                           // txt4.setText(actor);
                           JSONArray arr2 = mov.getJSONArray("i");
                           for(int i=0;i<arr2.length();i++){
                               if(i==1) txt5.setText(arr.get(i).toString());
                           }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
            }
        });

    }

}