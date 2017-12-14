package com.example2017.android.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.view.View;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catorgy_Post extends AppCompatActivity {
    private DatabaseReference mdatabase;
    final ArrayList<String> arrayList=new ArrayList<>();
    private Uri imageuri = null;
    boolean flag;

    private StorageReference s;
    EditText input;
    Bitmap resized;
    String k, InputCatorgy;
    ImageButton imageButton2;
    public static final int gallery_Intent = 3;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catorgy__post);

        Firebase.setAndroidContext(this);
        s = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("catorgy");
        progressDialog = new ProgressDialog(this);

        input = (EditText) findViewById(R.id.nameOfcatorgy);

        imageButton2 = (ImageButton) findViewById(R.id.catorgy);



    }


    public void select_imageOfcatorgy(android.view.View view)
    {


        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, gallery_Intent);

    }

    public void pushcat(android.view.View v) {


          add_catorgy();
    }


    public String getKeyOfcatorgy() {
        mdatabase.orderByChild("catorgy_name")
                .equalTo("sport")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String clubkey = childSnapshot.getKey();
                            k = clubkey;
                            Toast.makeText(getApplicationContext(), clubkey, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        return k;
    }


    public void Search(final String catorgy) {
//Still under development
        Query query=mdatabase.orderByChild("catorgy_name");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Datainput facts = snapshot.getValue(Datainput.class);


                arrayList.add(facts.getCatorgy_name().toString());
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
            // ....

        });
        System.out.println(flag);
    }


    public void add_catorgy(){

        if(!TextUtils.isEmpty(input.getText().toString() )&&imageuri!=null){
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        StorageReference filebath = s.child("photos").child(imageuri.getLastPathSegment());
        filebath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri down = taskSnapshot.getDownloadUrl();
                DatabaseReference newpost = mdatabase.push();
                newpost.child("catorgy_name").setValue(input.getText().toString());
                newpost.child("catorgy_image").setValue(down.toString());
                Toast.makeText(getApplicationContext(), "Post Succsesfull", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

    }else{
            if ((input.getText().toString().isEmpty()))

            {
                Toast.makeText(getApplicationContext(), "Description is Empty", Toast.LENGTH_LONG).show();
            }
            if (imageuri == null) {
                Toast.makeText(getApplicationContext(), "You should select Image", Toast.LENGTH_LONG).show();
            }
            if(flag==false){
                Toast.makeText(getApplicationContext(), "already Exist", Toast.LENGTH_LONG).show();

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == gallery_Intent && resultCode == RESULT_OK) {

            imageuri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);

                resized = Bitmap.createScaledBitmap(bitmap, 1000, 1020, true);


            } catch (IOException e) {
                e.printStackTrace();
            }


            imageButton2.setImageURI(imageuri);
        }


    }

}