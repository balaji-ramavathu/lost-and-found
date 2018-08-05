package com.ramavathubalaji.lost_found;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditPost extends AppCompatActivity {
    EditText editTitle,editDescription;
    Button editSubmit;
    String title,description,key,time,name,lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);


        editTitle=(EditText) findViewById(R.id.eteditTitle_edit);
        editDescription=(EditText) findViewById(R.id.eteditDescription_edit);
        editSubmit=(Button) findViewById(R.id.btnpost_edit);
        final RadioGroup radioGroup=(RadioGroup) findViewById(R.id.radiogrp_edit);
        final RadioButton rblost=(RadioButton) findViewById(R.id.rblost_edit);
        final RadioButton rbfound=(RadioButton) findViewById(R.id.rbfound_edit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            description=extras.getString("description");
            key=extras.getString("key");
            name=extras.getString("name");
            lf=extras.getString("lf");


        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
        Calendar calendar = Calendar.getInstance();
        time = simpleDateFormat.format(calendar.getTime());

        System.out.println("key from editpost"+key);
        editTitle.setText(title);
        editDescription.setText(description);
        if(lf.equals("lost"))
        {
            radioGroup.check(R.id.rblost_edit);
        }
        else if(lf.equals("found"))
        {
            radioGroup.check(R.id.rbfound_edit);
        }



        editSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id=radioGroup.getCheckedRadioButtonId();
                RadioButton rbselected=(RadioButton) findViewById(id);
                String rbtext=rbselected.getText().toString();
                //Toast.makeText(PostActivity.this,rbtext,Toast.LENGTH_LONG);
                if(rbtext.equals("Lost"))
                {
                    lf="lost";
                }
                else if(rbtext.equals("Found"))
                {
                    lf="found";
                }


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("lost").child(key);
                String newtitle=editTitle.getText().toString();
                String newdescription=editDescription.getText().toString();

                DatabaseReference myChild1Ref = myRef.child("data");
                myChild1Ref.setValue(newtitle + "_" + newdescription + "_" + time + "_" + name+"_"+lf);
                Toast.makeText(EditPost.this,"Updated Successfully",Toast.LENGTH_LONG);
                finish();
                Intent i = new Intent(getApplicationContext(), YourPosts.class);
                i.putExtra("update adapter",true);
                startActivity(i);


            }
        });







    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent i = new Intent(getApplicationContext(), YourPosts.class);
        startActivity(i);

    }
}
