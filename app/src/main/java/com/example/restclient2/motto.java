package com.example.restclient2;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class motto extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motto);

        //włączenie opcji cofania do poprzedniej aktywności
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView motto_dnia = (TextView) findViewById(R.id.textView);

        //trzeba zmienić adres na adres serwera lokalnego
        String url = "http://192.168.1.44:8080/Restfull/rest/motto";

        //stworzenie kolejki
        RequestQueue queue = Volley.newRequestQueue(this);

        //utworzenie rządania
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String motto_text = response.getString("motto");
                            motto_dnia.setText(motto_text);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                motto_dnia.setText("Nie udało się uzyskać połączenia. Sprawdź adres ip, czy urzadzenia są w tej samej sieci" +
                        "oraz czy sieć jest prywatna");

            }
        });


        //dodanie rządania na kolejkę
        queue.add(jsonObjectRequest);

}
}