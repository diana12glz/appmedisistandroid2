package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Citas extends AppCompatActivity {

    TextView textViewGuardarCitaCreada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textViewGuardarCitaCreada=findViewById(R.id.textViewGuardarCitaCreada);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        textViewGuardarCitaCreada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}