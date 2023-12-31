package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail, textInputEditTextPassword, textInputEditTextName;
    Button buttonSignUp;
    TextView loginText;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextName=findViewById(R.id.name);
        textInputEditTextEmail=findViewById(R.id.email);
        textInputEditTextPassword=findViewById(R.id.password);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        //checar id de login text
        loginText=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "LLEGOOO", Toast.LENGTH_SHORT).show();

                String name, password, email;
                name= String.valueOf(textInputEditTextName.getText());
                password= String.valueOf(textInputEditTextPassword.getText());
                email= String.valueOf(textInputEditTextEmail.getText());

                if(!name.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[3];
                            field[0] = "name";
                            field[1] = "email";
                            field[2] = "password";
                            String[] data = new String[3];
                            data[0] = name;
                            data[1] = email;
                            data[2] = password;
                            PutData putData = new PutData("http://192.168.18.34/LoginRegister/LogIn-SignUp-master/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //Log.i("PutData", result);
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),PacienteCrear.class);
                                        startActivity(intent);
                                        finish();

                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"Todos los campos son requeridos",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}