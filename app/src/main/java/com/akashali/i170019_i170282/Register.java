package com.akashali.i170019_i170282;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {
    TextView signinhere;
    Button registerbutton1;
    TextInputEditText getemailaddress,getpassword,getpassword1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signinhere=findViewById(R.id.signinhere);
        getemailaddress = findViewById(R.id.getemailaddress1);
        getpassword1 = findViewById(R.id.getcreatepassword);
        getpassword = findViewById(R.id.getretypepassword);
        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });
        registerbutton1=findViewById(R.id.registerbutton1);
        registerbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Useremail = getemailaddress.getText().toString();
                String Userpass=getpassword1.getText().toString();
                String Userpass1 = getpassword.getText().toString();
                if(!Useremail.equals("") && !Userpass1.equals("") && !Userpass.equals(""))
                {

                    if (Userpass1.equals(Userpass))
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
                                data[0] = Useremail;
                                data[1] = Userpass1;
                                PutData putData = new PutData("http://192.168.43.203/i170019_i170282/signup.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign Up Success"))
                                        {
                                            Toast.makeText(Register.this,result,Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(Register.this,CreateProfile.class);
                                            intent.putExtra("email",Useremail);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(Register.this,result,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Register.this,"Password not matched!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Register.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }

                //Intent intent=new Intent(Register.this,CreateProfile.class);
                //startActivity(intent);
                //finish();
            }
        });
    }
}