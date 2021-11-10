package com.example.restclient2;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Wyniki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyniki);


        //dodanie opcji cofania do rodzica
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //odczytanie wartości z intenta
        Intent intent = getIntent();
            String score = intent.getStringExtra("score");
           String max_score = intent.getStringExtra("max_score");

            TextView out_score = (TextView)findViewById(R.id.textView3);

            //wyświetlenie wyników
            String wynik = score+"/"+max_score;
            out_score.setText(wynik);



    }
}