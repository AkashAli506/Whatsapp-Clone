package com.akashali.i170019_i170282;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.ByteArrayOutputStream;

public class CreateProfile extends AppCompatActivity {
    Integer REQUEST_CAMERA=1, SELECT_IMAGE=0;
    ImageView accountimage;
    MaterialButton savebutton;
    TextInputEditText fname,lname,dob,bio,phone;
    Uri dp;
    String gender;
    Chip male,female,nosay;
    String selectedChipData;
    String imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        savebutton=findViewById(R.id.savebutton);
        fname = findViewById(R.id.getfirstname);
        lname = findViewById(R.id.getlastname);
        dob = findViewById(R.id.getdateofbirth);
        male = findViewById(R.id.male);
        phone = findViewById(R.id.getphonenumber);
        female = findViewById(R.id.female);
        nosay = findViewById(R.id.prefernottosay);
        bio = findViewById(R.id.getfullbio);
        Intent temp=getIntent();
        String getEmail=temp.getStringExtra("email");

        CompoundButton.OnCheckedChangeListener checkedChangeListener= new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    selectedChipData=compoundButton.getText().toString();
                    //Toast.makeText(ServiceProvidersListView.this,selectedChipData,Toast.LENGTH_SHORT).show();
                    getgender(selectedChipData);

                }
            }
        };
        male.setOnCheckedChangeListener(checkedChangeListener);
        female.setOnCheckedChangeListener(checkedChangeListener);
        nosay.setOnCheckedChangeListener(checkedChangeListener);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fname = fname.getText().toString();
                String Lname = lname.getText().toString();
                String Dob = dob.getText().toString();
                String Bio = bio.getText().toString();
                String Phone = phone.getText().toString();
                String gender =selectedChipData;
                if(!Fname.equals("") && !Lname.equals("") && !Dob.equals("") && !Bio.equals("") && !Phone.equals("") && !gender.equals(""))
                {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[8];
                            field[0] = "email";
                            field[1] = "fullname";
                            field[2] = "lastname";
                            field[3] = "dob";
                            field[4] = "gender";
                            field[5] = "phno";
                            field[6] = "bio";
                            field[7] = "image";
                            //Creating array for data
                            String[] data = new String[8];
                            data[0] = getEmail;
                            data[1] = Fname;
                            data[2] = Lname;
                            data[3] = Dob;
                            data[4] = gender;
                            data[5] = Phone;
                            data[6] = Bio;
                            data[7] = imageString;
                            PutData putData = new PutData("http://192.168.43.203/i170019_i170282/createprofile.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Profile Success"))
                                    {
                                        Toast.makeText(CreateProfile.this,result,Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(CreateProfile.this,HomePage.class);
                                        intent.putExtra("email",getEmail);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(CreateProfile.this,result,Toast.LENGTH_LONG).show();
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
                    Toast.makeText(CreateProfile.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }


                //Intent intent = new Intent(CreateProfile.this, HomePage.class);
                //startActivity(intent);

            }
        });

        accountimage=findViewById(R.id.profilepic);
        accountimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            if(requestCode==REQUEST_CAMERA)
            {
                assert data != null;
                Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap1 = bitmap;
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 60, baos);
                byte[] imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                //result.putExtra("image",imageString);
                //decode base64 string to image
                imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                accountimage.setImageBitmap(decodedImage);
                //Uri dp = getImageUri(CreateProfile.this,bitmap);
                //accountimage.setImageURI(dp);
            }
            else if(requestCode==SELECT_IMAGE)
            {
                assert data != null;
                Uri selectImage=data.getData();
                accountimage.setImageURI(selectImage);
                //accountimage.setVisibility(View.GONE);
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    private void SelectImage() {
        final CharSequence[] items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(CreateProfile.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera"))
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }
                else if(items[i].equals("Gallery"))
                {
                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent,SELECT_IMAGE);

                } else if (items[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
    private void getgender(String selectedChipData) {
        gender = selectedChipData;
    }
}