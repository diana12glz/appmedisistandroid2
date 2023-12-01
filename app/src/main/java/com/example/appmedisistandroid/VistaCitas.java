package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class VistaCitas extends AppCompatActivity {

    TextView textViewAddCita, textNombreCita, textApellidosCita, textTipoCita, textFechaCita, textHoraCita, textCosto;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_citas);
        textNombreCita=findViewById(R.id.textNombreCita);
        textApellidosCita=findViewById(R.id.textApellidosCita);
        textTipoCita=findViewById(R.id.textTipoCita);
        textFechaCita=findViewById(R.id.textFechaCita);
        textHoraCita=findViewById(R.id.textHoraCita);
        textCosto=findViewById(R.id.textCosto);
        textViewAddCita=findViewById(R.id.textViewAddCita);
        textViewAddCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),
                        Citas.class);
                startActivity(intent);
                finish();
            }
        });
        LeerWs();

    }

    private void LeerWs(){
        String url ="http://192.168.18.34:4000/api/citas/2";
        StringRequest postRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Aquí puedes acceder a los valores individuales del objeto dentro del JSONArray
                        String nombre = jsonObject.getString("NombreP");
                        String apellido = jsonObject.getString("ApellidoP");
                        System.out.println("apellido = " + apellido);
                        textNombreCita.setText(jsonObject.getString("NombreP"));
                        textApellidosCita.setText(jsonObject.getString("ApellidoP"));
                        textTipoCita.setText(jsonObject.getString("TipoCita"));
                        textFechaCita.setText(jsonObject.getString("FechaCita"));
                        textHoraCita.setText(jsonObject.getString("Horainicio"));
                        textCosto.setText(jsonObject.getString("Costos"));
                        // ...

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    Log.e("error",error.getMessage());
                }
                System.out.println("error al tracer dato"+ error.getMessage());


            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }
}