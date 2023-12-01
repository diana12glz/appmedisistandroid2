package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VistaCitas extends AppCompatActivity {

    TextView textViewAddCita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_citas);

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
    }
}