package com.example2017.android.admin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class shop_in_code extends AppCompatActivity {
    DatabaseReference shop;

    EditText marketname,value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_code);
        Firebase.setAndroidContext(this);

        marketname=(EditText)findViewById(R.id.editText);
        value=(EditText)findViewById(R.id.editText2);

        shop= FirebaseDatabase.getInstance().getReference().child("codes");


    }



    public void add_all(String c,String k,String v)
    {
        //this function add one value to all codes in the same time
        shop.child(c).child(k).setValue(v);
    }



    public void clear(View v){
        marketname.getText().clear();
        value.getText().clear();

    }

    //on click to button
    public void add(View v){
        //certain that fields not empty
        if(!TextUtils.isEmpty(marketname.getText().toString().trim())  &&  !TextUtils.isEmpty(value.getText().toString().trim()))
        {
            shop.addChildEventListener(new ChildEventListener()
            {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //to get the key of first node
                    String key = dataSnapshot.getKey();
                    add_all(key.toString(), marketname.getText().toString().trim(), value.getText().toString().trim());
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {

            if(marketname.getText().toString().trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Market Name is Empty",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Value Name is Empty",Toast.LENGTH_LONG).show();
            }
        }
    }
}
