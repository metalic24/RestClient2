package com.example.restclient2;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        String url = "http://192.168.1.2:8080/Restfull/rest/quiz";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            pytania(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // 3. Dodanie żądania na kolejkę.
        queue.add(jsonObjectRequest);




    }
    public void pytania(JSONObject jsonObject) throws JSONException {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layaut);

        JSONArray array = (JSONArray)jsonObject.get("pytania");



        for(int i=0; i<array.length(); i++)
        {
            JSONObject pytanie = (JSONObject) array.get(i);

            String numer_pytania = (String) pytanie.get("numer_pytania");
            TextView question_number = new TextView(this);
            question_number.setText(numer_pytania);
            layout.addView(question_number);

            String question = (String) pytanie.get("pytanie");
            TextView question_text = new TextView(this);
            question_text.setText(question);

            layout.addView(question_text);

            RadioGroup answers = new RadioGroup(this);
            RadioButton ansA = new RadioButton(this);
            RadioButton ansB = new RadioButton(this);
            RadioButton ansC = new RadioButton(this);
            RadioButton ansD = new RadioButton(this);

            ansA.setText((String) pytanie.get("odp_a"));
            ansB.setText((String) pytanie.get("odp_b"));
            ansC.setText((String) pytanie.get("odp_c"));
            ansD.setText((String) pytanie.get("odp_d"));



            answers.addView(ansA);
            answers.addView(ansB);
            answers.addView(ansC);
            answers.addView(ansD);

            layout.addView(answers);

            


        }
    }
}