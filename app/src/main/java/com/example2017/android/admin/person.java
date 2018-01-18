package com.example2017.android.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        add_person_names(code.getText().toString().trim(),
                p1.getText().toString().trim(),
                p2.getText().toString().trim(),
                p3.getText().toString().trim(),
                p4.getText().toString().trim(),
                p5.getText().toString().trim());

        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
    }




     public void add_person_names(String code,String a1,String a2,String a3,String a4,String a5)
    {
        CodeValue c=new CodeValue();
        c.setPerson1(a1);
        c.setPerson2(a2);
        c.setPerson3(a3);
        c.setPerson4(a4);
        c.setPerson5(a5);
        UtilityFirebase.child(code).setValue(c);



}}
