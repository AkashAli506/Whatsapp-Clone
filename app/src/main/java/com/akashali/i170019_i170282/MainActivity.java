package com.akashali.i170019_i170282;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button signinbutton,registerbutton;
    public static ArrayList<Modal> modalArrayList=new ArrayList<>();
    Modal model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinbutton=findViewById(R.id.signinbutton);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);
            }
        });
        //retrieveData();




        registerbutton=findViewById(R.id.registerbutton);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url="http://192.168.43.203/i170019_i170282/read.php";
        StringRequest request=new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        modalArrayList.clear();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String success= jsonObject.getString("success");
                            JSONArray jsonArray=jsonObject.getJSONArray("users");
                            if(success.equals("1"))
                            {
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    String id=object.getString("id");
                                    String fullname=object.getString("fullname");
                                    String lastname=object.getString("lastname");
                                    String email=object.getString("email");
                                    String dob=object.getString("dob");
                                    String gender=object.getString("gender");
                                    String phno=object.getString("phno");
                                    String bio=object.getString("bio");
                                    Log.i("test1",fullname);


                                }
                            }
                        }catch (JSONException e){
                            Log.i("test1","no");
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.i("test1","nono");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "afas");
                return params;
            }
        };
        requestQueue.add(request);
    }
}