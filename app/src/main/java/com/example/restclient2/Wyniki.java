package com.example.restclient2;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Wyniki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyniki);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
            String score = intent.getStringExtra("score");
           String max_score = intent.getStringExtra("max_score");

            TextView out_score = (TextView)findViewById(R.id.textView3);
            out_score.setText(score+"/"+max_score);





    }
}