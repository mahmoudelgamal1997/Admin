package com.example2017.android.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.view.View;
import com.firebase.client.utilities.Utilities;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop extends AppCompatActivity {
    private Spinner spinnerCity,spinnerCatorgy;
    private DatabaseReference mdatabase,mmdatabase;
    ArrayList<String> arrayList_catorgy=new ArrayList<>();
    TextView txt;
    ArrayList<String> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Firebase.setAndroidContext(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("City");
        spinnerCity=(Spinner)findViewById(R.id.spinner);
        spinnerCatorgy=(Spinner)findViewById(R.id.spinner2);
        txt=(TextView)findViewById(R.id.textView);



        retrive_city();
        retrive_catorgy();

cityadd();

//array that can solve my problem
        arrayList.add("mahmoud");
        arrayList.add("yasser");
        arrayList.add("elgamal");




        //Adapter that recieve alist of city from fire base
        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, retrive_city());

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void
           onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {



           }

           @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

           }});




         ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList_catorgy );
    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    spinnerCatorgy.setAdapter(adapter2);
    spinnerCatorgy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
            spinnerCatorgy.setSelection(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

            spinnerCatorgy.setSelection(0);}});

}



    public void trial(android.view.View v){


    }





    public void retrive_catorgy()
    {
        mmdatabase=FirebaseDatabase.getInstance().getReference().child("catorgy");
        Query query=mmdatabase.orderByChild("catorgy_name");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               Datainput cc=dataSnapshot.getValue(Datainput.class);
               arrayList_catorgy.add(cc.getCatorgy_name());

                System.out.println(dataSnapshot);
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



    public ArrayList<String> retrive_city()
    {
        final ArrayList<String> arrayList_city=new ArrayList<>();

        final Query query=mdatabase.orderByChild("title");
      query.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              City c=dataSnapshot.getValue(City.class);
              arrayList_city.add(c.getTitle());
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

        return arrayList_city;
    }

    public void  cityadd()
    {




}}
