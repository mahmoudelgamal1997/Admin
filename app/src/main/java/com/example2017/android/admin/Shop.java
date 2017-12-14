package com.example2017.android.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.view.View;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {
    private Spinner spinnerCity,spinnerCatorgy;
    private DatabaseReference mdatabase,mmdatabase;
    ArrayList<String> arrayList_city=new ArrayList<>();
    ArrayList<String> arrayList_catorgy=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Firebase.setAndroidContext(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("City");
        spinnerCity=(Spinner)findViewById(R.id.spinner);
        spinnerCatorgy=(Spinner)findViewById(R.id.spinner2);

        retrive_city();
        retrive_catorgy();


        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList_city );

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerCity.setAdapter(adapter);

       spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void
           onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {

           }

           @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList_catorgy );

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerCatorgy.setAdapter(adapter2);

        spinnerCatorgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                spinnerCatorgy.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void trial(android.view.View v){

        System.out.println(arrayList_city);

    }






    public void retrive_catorgy()
    {
        mmdatabase=FirebaseDatabase.getInstance().getReference().child("catorgy");
        Query query=mmdatabase.orderByChild("catorgy_name");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Datainput cc=dataSnapshot.getValue(Datainput.class);
                arrayList_catorgy.add(cc.getCatorgy_name().toString());
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



    public void retrive_city()
    {
        Query query=mdatabase.orderByChild("title");
      query.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              City c=dataSnapshot.getValue(City.class);
              arrayList_city.add(c.getTitle().toString());
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
