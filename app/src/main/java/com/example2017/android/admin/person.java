package com.example2017.android.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.view.View;
import com.firebase.client.utilities.Utilities;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class person extends AppCompatActivity {
    DatabaseReference UtilityFirebase;

    EditText p1,p2,p3,p4,p5,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Firebase.setAndroidContext(this);

        UtilityFirebase= FirebaseDatabase.getInstance().getReference().child("codes");

        p1=(EditText)findViewById(R.id.person1);
        p2=(EditText)findViewById(R.id.person2);
        p3=(EditText)findViewById(R.id.person3);
        p4=(EditText)findViewById(R.id.person4);
        p5=(EditText)findViewById(R.id.person5);
        code=(EditText)findViewById(R.id.code);



    }






    public void per(android.view.View v){

        if(!TextUtils.isEmpty(code.getText().toString().trim())) {

            add_person_names(code.getText().toString().trim(),
                    p1.getText().toString().trim(),
                    p2.getText().toString().trim(),
                    p3.getText().toString().trim(),
                    p4.getText().toString().trim(),
                    p5.getText().toString().trim());

            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, person.class);
            startActivity(intent);
        }else{

            Toast.makeText(getApplicationContext(),"You should add code",Toast.LENGTH_SHORT).show();
        }

    }





     public void add_person_names(String code,String a1,String a2,String a3,String a4,String a5)
    {


        UtilityFirebase.child(code).child("person1").setValue(a1);
        UtilityFirebase.child(code).child("person2").setValue(a2);
        UtilityFirebase.child(code).child("person3").setValue(a3);
        UtilityFirebase.child(code).child("person4").setValue(a4);
        UtilityFirebase.child(code).child("person5").setValue(a5);



}}
