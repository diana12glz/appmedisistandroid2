package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatosPaciente extends AppCompatActivity {
    //private static final String API_URL = "http://192.168.1.149:4000/api/pacientes/2";

    TextView textViewNomDatPac,textViewApDatPac, textViewGenDatPac, textViewFechDatPac, textViewTelDatPac,textViewDirDatPac;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_paciente);
        textViewNomDatPac=findViewById(R.id.textViewNomDatPac);
        textViewApDatPac=findViewById(R.id.textViewApDatPac);
        textViewGenDatPac=findViewById(R.id.textViewGenDatPac);
        textViewFechDatPac=findViewById(R.id.textViewFechDatPac);
        textViewTelDatPac=findViewById(R.id.textViewTelDatPac);
        textViewDirDatPac=findViewById(R.id.textViewDirDatPac);

        LeerWs();

    }

    private void LeerWs(){
        String url ="http://192.168.18.34:4000/api/pacientes/2";
        StringRequest postRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Aquí puedes acceder a los valores individuales del objeto dentro del JSONArray
                        String nombre = jsonObject.getString("Nombre");
                        String apellido = jsonObject.getString("Apellido");
                        System.out.println("apellido = " + apellido);
                        textViewNomDatPac.setText(jsonObject.getString("Nombre"));
                        textViewApDatPac.setText(jsonObject.getString("Apellido"));
                        textViewGenDatPac.setText(jsonObject.getString("Genero"));
                        textViewFechDatPac.setText(jsonObject.getString("FechaNac"));
                        textViewTelDatPac.setText(jsonObject.getString("Telefono"));
                        textViewDirDatPac.setText(jsonObject.getString("Direccion"));
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