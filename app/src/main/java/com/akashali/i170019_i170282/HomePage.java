package com.akashali.i170019_i170282;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //NAVIGATION DRAWER VARIABLES START
    DrawerLayout drawerLayout;
    Integer REQUEST_CODE=1;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView chatRV;
    ImageView mainmenu,mediabtn;
    TextView text;
    ImageView profileImage,pIMG;
    List<String> msgs;
    //List<NewMessage> newMessages;
    List<ChatObject> MyChats;
    MyRvAdapter adapter;
    Bitmap image;
    MaterialButton searchserviceprovideronmapinput;
    Bitmap img;
    String name;
    String phone;
    String rcvId;
    //NAVIGATION DRAWER VARIABLES START
    String notificationKey=null;
    ImageView searchicon;
    TextView searchtext;
    de.hdodenhof.circleimageview.CircleImageView profilehomepage;

    ImageView homepagelogo;

    String getEmail;
    String id;
    String fullname;
    String lastname;
    String email;
    String dob;
    String gender;
    String phno;
    String bio;
    String value;
    Bitmap decodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent temp=getIntent();
        getEmail=temp.getStringExtra("email");
        retrieveData();

        MyChats=new ArrayList<>();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        mainmenu=findViewById(R.id.mainmenu);
        pIMG = findViewById(R.id.profileaccountimage);
        profilehomepage=findViewById(R.id.profilehomepage);

        msgs = new ArrayList<>();

        chatRV=findViewById(R.id.chatRV);
        RecyclerView.LayoutManager lm= new LinearLayoutManager(this);
        chatRV.setLayoutManager(lm);
        adapter=new MyRvAdapter(MyChats,this);
        chatRV.setAdapter(adapter);

        homepagelogo=findViewById(R.id.homepagelogo);
        homepagelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(HomePage.this, MessageActivity.class);
                //startActivity(intent);
            }
        });


        searchicon=findViewById(R.id.searchicon);
        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SearchUser.class);
                startActivity(intent);
            }
        });
        searchtext=findViewById(R.id.searchtext);
        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SearchUser.class);
                startActivity(intent);
            }
        });
        profilehomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog( HomePage.this,R.style.BottomSheetDialogTheme);
                View bottomSheetView= LayoutInflater.from(HomePage.this).inflate(R.layout.layout_bottom_sheet,(LinearLayout)findViewById(R.id.bottomsheet_cl));
                TextView myname=bottomSheetView.findViewById(R.id.myname);
                TextView myphone=bottomSheetView.findViewById(R.id.myphone);
                TextView mygender=bottomSheetView.findViewById(R.id.mygender);
                TextView myage=bottomSheetView.findViewById(R.id.myage);
                TextView mybio=bottomSheetView.findViewById(R.id.mybio);
                ImageView myimage=bottomSheetView.findViewById(R.id.myimage);
                myimage.setImageBitmap(decodedImage);
                myname.setText(fullname+" "+lastname);
                myphone.setText(phno);
                mygender.setText(gender);
                myage.setText(" 23");
                mybio.setText(bio);
                bottomSheetView.findViewById(R.id.myeditprofile).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomePage.this, EditProfile.class);
                        intent.putExtra("email",getEmail);
                        intent.putExtra("fullname",fullname);
                        intent.putExtra("lastname",lastname);
                        intent.putExtra("dob",dob);
                        intent.putExtra("phno",phno);
                        intent.putExtra("bio",bio);
                        intent.putExtra("image",value);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }
        });
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);




        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        adapter.setOnItemClickListener(new MyRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = MyChats.get(position).getName();

            }
        });




    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
        }
        return true;
    }
    private void retrieveData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url="http://192.168.43.203/i170019_i170282/read.php";
        StringRequest request=new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String success= jsonObject.getString("success");
                            JSONArray jsonArray=jsonObject.getJSONArray("users");
                            if(success.equals("1"))
                            {
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    id=object.getString("id");
                                    fullname=object.getString("fullname");
                                    lastname=object.getString("lastname");
                                    email=object.getString("email");
                                    dob=object.getString("dob");
                                    gender=object.getString("gender");
                                    phno=object.getString("phno");
                                    bio=object.getString("bio");
                                    value=object.getString("image");
                                    Log.i("test1",fullname);

                                    byte[] imageBytes = Base64.decode(value, Base64.DEFAULT);
                                    decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                    profilehomepage.setImageBitmap(decodedImage);

                                    View header = navigationView.getHeaderView(0);
                                    text = (TextView) header.findViewById(R.id.username);
                                    text.setText(fullname+" "+lastname);
                                    TextView useremailheader = (TextView) header.findViewById(R.id.useremailheader);
                                    useremailheader.setText(email);
                                    profileImage=(ImageView) header.findViewById(R.id.circleImageView);
                                    profileImage.setImageBitmap(decodedImage);

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
                        Toast.makeText(HomePage.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.i("test1","nono");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                Map<String, String> params = new HashMap<String, String>();
                params.put("email", getEmail);
                return params;
            }
        };
        requestQueue.add(request);
    }

}