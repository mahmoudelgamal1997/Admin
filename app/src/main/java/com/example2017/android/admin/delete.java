package com.example2017.android.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class delete extends AppCompatActivity {
  private   DatabaseReference db ,code;
  private   Spinner spinner;


    //to save code from clear
    //we put one child as least
    private int minmumChildreninCode=1;
    private String shop_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Firebase.setAndroidContext(this);

        db = FirebaseDatabase.getInstance().getReference().child("CodeValue");
        code = FirebaseDatabase.getInstance().getReference().child("codes");

        spinner = (Spinner) findViewById(R.id.spinner2);


        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.text_style,
                db
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(R.id.textView_style);
                textView.setText(model);
                shop_selected = model;

            }

        };
        spinner.setAdapter(firebaseListAdapter);






    }


    public void okDelete(View v) {

        Toast.makeText(getApplicationContext(),shop_selected,Toast.LENGTH_LONG).show();


        code.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
     if (dataSnapshot.getChildrenCount()>minmumChildreninCode)
     {

    String key = dataSnapshot.getKey();
    code.child(key).child(shop_selected).removeValue();
    db.child(shop_selected).removeValue();

}
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

        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();



    }

}