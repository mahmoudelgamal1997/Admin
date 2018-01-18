package com.example2017.android.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        shop.child(c).child(k).setValue(v);
    }



    public void add(View v){

        shop.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //to get the key of first node
                String key=dataSnapshot.getKey();
                add_all(key.toString(),marketname.getText().toString(),value.getText().toString());
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
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



    }




}
