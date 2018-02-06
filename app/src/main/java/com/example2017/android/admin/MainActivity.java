package com.example2017.android.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  Firebase.setAndroidContext(this);


    }





    public void start(View v){

        Intent intent = new Intent(this,OfferCard.class);
        startActivity(intent);

    }
    public void eldalel(View v){

        Intent intent = new Intent(this,EldalelSetting.class);
        startActivity(intent);

    }




    public void test(View v){

        Intent intent = new Intent(this,TestRetrieve.class);
        startActivity(intent);

    }



}
