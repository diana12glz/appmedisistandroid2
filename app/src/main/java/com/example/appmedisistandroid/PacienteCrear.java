package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PacienteCrear extends AppCompatActivity {
    String url = "http://192.168.1.149:4000/api/pacientes";

    TextView GuardarCuentaPacienteUsuarioo;
    TextInputEditText textInputEditTextNombrePacientee, textInputEditTextApellidoPacientee, textInputEditTextDireccionPacientee;
    RadioButton radioButtonMujerPacc;
    EditText editTextDateFechaNacPacc,editTextPhoneTelPac;
    int idUsuario;

    {
        idUsuario = 3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_crear);
        textInputEditTextNombrePacientee = findViewById(R.id.textInputEditTextNombrePacientee);
        textInputEditTextApellidoPacientee = findViewById(R.id.textInputEditTextApellidoPacientee);
        textInputEditTextDireccionPacientee = findViewById(R.id.textInputEditTextDireccionPacientee);
        radioButtonMujerPacc=findViewById(R.id.radioButtonMujerPacc);
        editTextDateFechaNacPacc=findViewById(R.id.editTextDateFechaNacPacc);
        editTextPhoneTelPac=findViewById(R.id.editTextPhoneTelPac);

        final String Nombre = textInputEditTextNombrePacientee.getText().toString();
        final String Apellido = textInputEditTextApellidoPacientee.getText().toString();
        final String Direccion = textInputEditTextDireccionPacientee.getText().toString();
       // final char Genero = radioButtonMujerPacc.getText().toString();
        final String FechaNac = editTextDateFechaNacPacc.getText().toString();
        final String Telefono = editTextPhoneTelPac.getText().toString();

        GuardarCuentaPacienteUsuarioo=findViewById(R.id.GuardarCuentaPacienteUsuarioo);

        GuardarCuentaPacienteUsuarioo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //agregarPaciente();
               // EnviarWs(Nombre,Apellido,Direccion,FechaNac,Telefono);
                EnviarWs();


            }
        });


    }
    //String Nombre, String Apellido, String Direccion , String FechaNac , String Telefono
    private void EnviarWs() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject=new JSONObject(response);

                        Toast.makeText(getApplicationContext(), "ID= " + jsonObject.getString("id_Paciente"), Toast.LENGTH_LONG).show();
                        System.out.println("response = " + response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    Log.e("Error.Response", new String(error.networkResponse.data));
                } else {
                    Log.e("Error", "Unknown error occurred!");
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", "diana");
                params.put("Apellido", "gonz");
                params.put("Genero", "M");
                params.put("FechaNac", "2000-12-12");
                params.put("Telefono", "8713644674");
                params.put("Direccion", "holhfuishfiaua");
                params.put("idUsuario", "6");
                return params;
            }
        };
        Volley.newRequestQueue(null).add(postRequest);
    }
}
