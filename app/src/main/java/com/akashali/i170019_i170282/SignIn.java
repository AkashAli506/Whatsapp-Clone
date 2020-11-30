package com.akashali.i170019_i170282;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignIn extends AppCompatActivity {
    TextView registerhere;
    TextInputEditText getemailaddress,getpassword;
    Button signinbutton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        registerhere = findViewById(R.id.registerhere);
        getemailaddress = findViewById(R.id.getemailaddress);
        getpassword = findViewById(R.id.getpassword);

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
        getemailaddress = findViewById(R.id.getemailaddress);
        getpassword = findViewById(R.id.getpassword);
        signinbutton1 = findViewById(R.id.signinbutton1);
        signinbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = getemailaddress.getText().toString();
                String password = getpassword.getText().toString();

                if(!email.equals("") && !password.equals("") )
                {


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.43.203/i170019_i170282/Login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success"))
                                    {
                                        Toast.makeText(SignIn.this,result,Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(SignIn.this,HomePage.class);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(SignIn.this,result,Toast.LENGTH_LONG).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignIn.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}