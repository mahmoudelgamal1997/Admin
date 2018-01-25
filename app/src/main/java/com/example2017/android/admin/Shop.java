package com.example2017.android.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.view.View;
import com.firebase.client.utilities.Utilities;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop extends AppCompatActivity {
    private Spinner spinnerCity, spinnerCatorgy;
    private DatabaseReference catorgy_database, city_database;
    private StorageReference s;

    ProgressDialog progressDialog;
    TextView txt;
    String city_selected, catorgy_selected;
    public static final int gallery=2;
    private Uri imageuri=null;
    ImageButton imageButton;
    EditText editText_shop,editText_home,editText_mobile,editText_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Firebase.setAndroidContext(this);
        city_database = FirebaseDatabase.getInstance().getReference().child("City");
        catorgy_database = FirebaseDatabase.getInstance().getReference().child("catorgy");
        s = FirebaseStorage.getInstance().getReference();

        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        spinnerCatorgy = (Spinner) findViewById(R.id.spinner_catorgy);
        imageButton=(ImageButton)findViewById(R.id.imageButton);

        editText_shop=(EditText)findViewById(R.id.editText_shop);
        editText_mobile=(EditText)findViewById(R.id.editText_mobile);
        editText_home=(EditText)findViewById(R.id.editText_home);
        editText_details=(EditText)findViewById(R.id.editText_details);


        progressDialog=new ProgressDialog(this);


        FirebaseListAdapter<City> cityListAdapter = new FirebaseListAdapter<City>(
                this,
                City.class,
                R.layout.text_style,
                city_database
        ) {
            @Override
            protected void populateView(android.view.View v, City model, int position) {
                TextView txt = (TextView) v.findViewById(R.id.textView_style);
                txt.setText(model.getTitle());

                //taking select of user
                //and put it in variable
                city_selected = model.getTitle();

            }
        };
spinnerCity.setAdapter(cityListAdapter);

        FirebaseListAdapter<Datainput> catorgyListAdapter = new FirebaseListAdapter<Datainput>(
                this,
                Datainput.class,
                R.layout.text_style,
                catorgy_database
        ) {
            @Override
            protected void populateView(android.view.View v, Datainput model, int position) {

                TextView textView = (TextView) v.findViewById(R.id.textView_style);
                textView.setText(model.getCatorgy_name());

                //taking select of user
                //and put it in variable
                catorgy_selected = model.getCatorgy_name();
            }
        };

spinnerCatorgy.setAdapter(catorgyListAdapter);
    }



    public void upload (android.view.View view){

     if (!TextUtils.isEmpty(editText_shop.getText().toString()) &&imageuri!=null){
         final DatabaseReference post=catorgy_database.child(catorgy_selected).child(catorgy_selected).child(city_selected).child(editText_shop.getText().toString().toLowerCase().trim());

         progressDialog.setMessage("Wait..");
         progressDialog.show();
         progressDialog.setCancelable(false);
        StorageReference filebath = s.child("photos").child(imageuri.getLastPathSegment());
        filebath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri down = taskSnapshot.getDownloadUrl();
                post.child("catorgy_name").setValue(editText_shop.getText().toString());
                post.child("catorgy_image").setValue(down.toString());
                post.child("shop_details").setValue(editText_details.getText().toString().toLowerCase().trim());
                post.child("shop_mobile").setValue(editText_mobile.getText().toString().toLowerCase().trim());
                post.child("shop_home").setValue(editText_home.getText().toString().toLowerCase().trim());



                Toast.makeText(getApplicationContext(), "Post Succsesfull", Toast.LENGTH_LONG).show();
               progressDialog.dismiss();

            }
        });

    }else {
         if (TextUtils.isEmpty(editText_shop.getText().toString())){
             Toast.makeText(getApplication(),"Enter shop name",Toast.LENGTH_LONG).show();
         }else{
             Toast.makeText(getApplication(),"Select an image",Toast.LENGTH_LONG).show();

         }

     }

    }

public void select_shopimage(android.view.View v){

    Intent intent=new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent,gallery);

}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallery&&resultCode==RESULT_OK){

            imageuri=data.getData();
            imageButton.setImageURI(imageuri);

            Bitmap resized = null;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);

                resized = Bitmap.createScaledBitmap(bitmap, 1000, 1020, true);


            } catch (IOException e) {
                e.printStackTrace();
            }

            imageButton.setImageBitmap(resized);
        }

        }

}