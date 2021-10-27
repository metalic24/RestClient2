package com.example.restclient2;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView quiz_pytania = (TextView) findViewById(R.id.textView2);
        String url = "http://192.168.1.44:8080/Restfull/rest/quiz";

        // RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
               (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                       @Override
                        public void onResponse(JSONObject response) {
                            quiz_pytania.setText(response.toString());

                       }

                    },new Response.ErrorListener() {

                      @Override
                      public void onErrorResponse(VolleyError error) {

             }
                 });



        //   queue.add(jsonObjectRequest);



    }
}