package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCitas, textViewperfilmientras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCitas=findViewById(R.id.textViewCitas);
        textViewperfilmientras=findViewById(R.id.textViewperfilmientras);
        textViewCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), VistaCitas.class);
                startActivity(intent);
                finish();
            }
        });

        textViewperfilmientras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), DatosPaciente.class);
                startActivity(intent);
                finish();
            }
        });


    }
}