package com.example.restclient2;

import android.content.Intent;
import android.view.View;
import android.widget.*;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //dodanie opcji cofania
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //trzeba zmienić adres ip na adres serwera lokalnego
        String url = "http://192.168.1.44:8080/Restfull/rest/quiz";

        //utworzenie kolejki
        RequestQueue queue = Volley.newRequestQueue(this);

        //utworzenie rządania
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


        //dodanie rządania do kolejki
        queue.add(jsonObjectRequest);




    }
    public void pytania(JSONObject jsonObject) throws JSONException {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layaut);

        JSONArray array = (JSONArray)jsonObject.get("pytania");
        List<RadioGroup> answers_list  = new ArrayList<RadioGroup>();
        String[] poprawna = new String[array.length()];


        //odczytanie danych z obj json i wyświetlenie ich

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

            ansA.setId((int) 1);
            ansB.setId((int) 2);
            ansC.setId((int) 3);
            ansD.setId((int) 4);

            poprawna[i]= (String) pytanie.get("poprawna");


            answers.addView(ansA);
            answers.addView(ansB);
            answers.addView(ansC);
            answers.addView(ansD);

            layout.addView(answers);
            answers_list.add(answers);




        }

        Button check = new Button(this);
        check.setText("Sprawdź odpowiedzi");
        TextView wynik = new TextView(this);


        layout.addView(check);

        //sprawdzenie odpowiedzi i wysłanie wyniku
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score =0;
                int i=0;
                String odp = null;
                for (RadioGroup answer: answers_list
                ) {
                    

                    int r = (int) answer.getCheckedRadioButtonId();

                    switch (r)
                    {
                        case 1:
                            odp="a";
                            break;
                        case 2:
                            odp="b";
                            break;
                        case 3:
                            odp="c";
                            break;
                        case 4:
                            odp="d";

                            break;
                        default:
                            odp="";
                            break;
                    }



                    if(odp.equals(poprawna[i]))
                    {
                        score++;
                    }
                   i++;


                }

                //otwarcie nowej aktywności i wysłanie jej danych

                Intent intent = new Intent(quiz.this, Wyniki.class);
                intent.putExtra("score",String.valueOf(score));
                intent.putExtra("max_score",String.valueOf(i));
                startActivity(intent);




            }
        });


    }
}