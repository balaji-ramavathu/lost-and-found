package com.ramavathubalaji.lost_found;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    EditText title,description;
    Button postbtn;
    String titleS,descriptionS,emailS,dateS,lf;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedpreferences;
    int count,countf,lposts,fposts,dcount;
    int ik=1;
    long t;
    static PostActivity INSTANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        INSTANCE=this;

        title=(EditText) findViewById(R.id.eteditTitle);
        description=(EditText) findViewById(R.id.eteditDescription);
        postbtn=(Button) findViewById(R.id.btnpost);
        final RadioGroup radioGroup=(RadioGroup) findViewById(R.id.radiogrp);
        /*RadioButton rblost=(RadioButton) findViewById(R.id.rblost);
        RadioButton rbfound=(RadioButton) findViewById(R.id.rbfound);*/

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=radioGroup.getCheckedRadioButtonId();
                RadioButton rbselected=(RadioButton) findViewById(id);
                String rbtext=rbselected.getText().toString();
                Toast.makeText(PostActivity.this,rbtext,Toast.LENGTH_LONG);
                if(rbtext.equals("Lost"))
                {
                    lf="lost";
                }
                else if(rbtext.equals("Found"))
                {
                    lf="found";
                }

                System.out.println("just entered postonclick");
                /*Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    lf = extras.getString("lf from main22");

                }*/
                System.out.println("lf from post:"+lf);


                titleS = title.getText().toString();
                descriptionS = description.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
                dateS = simpleDateFormat.format(calendar.getTime());
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                emailS = user.getEmail();


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String key = database.getReference().child("lost").push().getKey();
                //System.out.println("post key:"+key);


                DatabaseReference myRef = database.getReference().child("lost").child(key);


                DatabaseReference myChild1Ref = myRef.child("data");
                myChild1Ref.setValue(titleS + "_" + descriptionS + "_" + dateS + "_" + emailS+"_"+lf);
                /*if(lf.equals("lost"))
                {
                    String key = database.getReference().child("lost").push().getKey();
                    //System.out.println("post key:"+key);


                    DatabaseReference myRef = database.getReference().child("lost").child(key);


                    DatabaseReference myChild1Ref = myRef.child("data");
                    myChild1Ref.setValue(titleS + "_" + descriptionS + "_" + dateS + "_" + emailS+"_"+lf);
                }
                if(lf.equals("found"))
                {
                    String key = database.getReference().child("lost").push().getKey();
                    //System.out.println("post key:"+key);

                    DatabaseReference myRef = database.getReference().child("lost").child(key);


                    DatabaseReference myChild1Ref = myRef.child("data");
                    myChild1Ref.setValue(titleS + "_" + descriptionS + "_" + dateS + "_" + emailS+"_"+lf);
                }*/


                //a.setukey(key);
                finish();
                Toast.makeText(PostActivity.this, "Successfully posted!", Toast.LENGTH_SHORT);
                Intent i = new Intent(getApplicationContext(), Main22Activity.class);
                /*i.putExtra("lf from post",lf);*/
                startActivity(i);


            }
        });



    }





}
