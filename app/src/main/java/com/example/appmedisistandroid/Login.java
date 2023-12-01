package com.example.appmedisistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputLayoutPasswordLogin, textInputLayoutNameLogin;
    Button buttonLogin;
    TextView SignText;
    ProgressBar progresslogin;

    public TextInputEditText getTextInputLayoutPasswordLogin() {
        return textInputLayoutPasswordLogin;
    }

    public TextInputEditText getTextInputLayoutNameLogin() {
        return textInputLayoutNameLogin;
    }

    public void setTextInputLayoutPasswordLogin(TextInputEditText textInputLayoutPasswordLogin) {
        this.textInputLayoutPasswordLogin = textInputLayoutPasswordLogin;
    }

    public void setTextInputLayoutNameLogin(TextInputEditText textInputLayoutNameLogin) {
        this.textInputLayoutNameLogin = textInputLayoutNameLogin;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputLayoutNameLogin=findViewById(R.id.nameLogin);
        textInputLayoutPasswordLogin=findViewById(R.id.passwordLogin);
        buttonLogin=findViewById(R.id.buttonLogin);
        SignText=findViewById(R.id.SignText);
        progresslogin=findViewById(R.id.progresslogin);

        SignText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "llego", Toast.LENGTH_SHORT).show();


                String nameLogin, passwordLogin;
                nameLogin= String.valueOf(textInputLayoutNameLogin.getText());
                passwordLogin= String.valueOf(textInputLayoutPasswordLogin.getText());

                System.out.println("name+password = " + nameLogin+passwordLogin);
                if(!nameLogin.equals("") && !passwordLogin.equals("")) {
                    progresslogin.setVisibility(View.VISIBLE);


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "name";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = nameLogin;
                            data[1] = passwordLogin;
                            PutData putData = new PutData("http://192.168.1.149/LoginRegister/LogIn-SignUp-master/login.php", "POST", field, data);
                            System.out.println("putData = " + putData);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progresslogin.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //Log.i("PutData", result);
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        
                                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
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