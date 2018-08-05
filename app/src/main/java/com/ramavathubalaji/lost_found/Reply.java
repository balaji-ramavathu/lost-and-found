package com.ramavathubalaji.lost_found;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Reply extends AppCompatActivity {

    String reciever_email,message,reciever_name,current_user_name;
    ImageButton addContent,imgbtnsend;
    EditText etmessage;
    Boolean isReached;
    LinearLayout layout;
    ScrollView scrollView;
    HashMap<String, Object> map;
    DatabaseReference myRef,myChild1Ref,myChild2Ref,myChild3Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            reciever_email=extras.getString("username");
        }
        System.out.println("username from reply: "+reciever_email);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.custom_chat_menu, null);
        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);
        Drawable drawable=getResources().getDrawable(R.drawable.gradient1);
        mActionBar.setBackgroundDrawable(drawable);
        addContent = (ImageButton)    customView.findViewById(R.id.action_chat_back);
        TextView chatTitle=(TextView) customView.findViewById(R.id.text_chat_appname);
        imgbtnsend=(ImageButton) findViewById(R.id.imgbtnsend);
        etmessage=(EditText) findViewById(R.id.etmessage);
        scrollView=(ScrollView) findViewById(R.id.chatscrollView);
        layout=(LinearLayout) findViewById(R.id.chatlinearlayout);

        /*etmessage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // if edittext has 10chars & this is not called yet, add new line
                if(etmessage.getText().length() ==25 && !isReached) {
                    etmessage.append("\n");
                    isReached = true;
                }
                // if edittext has less than 10chars & boolean has changed, reset
                if(etmessage.getText().length() < 25 && isReached) isReached = false;


            }

        });*/

        String[] split_reciever_email=reciever_email.split("@");
         reciever_name=split_reciever_email[0];
        chatTitle.setText(reciever_name);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        final String current_user_email=firebaseAuth.getCurrentUser().getEmail().toString();
        String[] split_array=current_user_email.split("@");
        current_user_name=split_array[0];
        System.out.println("current_user:"+current_user_name);

        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Reply.this, Main22Activity.class);
                finish();
                startActivity(i);
            }
        });



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        imgbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 message= etmessage.getText().toString();
                if(message.equals(""))
                {
                    Toast.makeText(Reply.this,"Message can't be empty",Toast.LENGTH_LONG);
                }

                if(!message.equals("")&&(!reciever_name.equals(""))&&(!current_user_name.equals("")))
                {
                    System.out.println("here at !message.equals(null)");
                    String key = database.getReference().child("messages").push().getKey();
                     myRef = database.getReference().child("messages").child(key);
                     myChild1Ref = myRef.child("data");
                    myChild1Ref.setValue(message+"_"+current_user_name+"_"+reciever_name);
                     /*myChild2Ref = myRef.child("reciever");
                    myChild2Ref.setValue(reciever_name);
                    myChild3Ref=myRef.child("sender");
                    myChild3Ref.setValue(current_user_name);*/
                    etmessage.setText("");
                }

            }
        });
        DatabaseReference userReference=database.getReference().child("messages");
        userReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                map = (HashMap<String, Object>) dataSnapshot.getValue();
                if((map!=null))
                {
                    String mapdata=map.get("data").toString();
                    System.out.println("mapdata:"+mapdata);
                    // System.out.println("reciever_name,current_user_name:"+reciever_name+","+current_user_name);

                    String dataarray[] = mapdata.split("_");
                    String messageS = dataarray[0];
                    if (dataarray[1].equals(current_user_name)&&dataarray[2].equals(reciever_name))
                    {
                        //msg was sent by you
                        addMessageBox(messageS,1);
                    }
                    if (dataarray[1].equals(reciever_name)&&dataarray[2].equals(current_user_name))
                    {
                        //msg was sent by other person
                        addMessageBox(messageS,2);
                    }
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

        /*userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> items=dataSnapshot.getChildren();
                System.out.println("into ondatachange,reply.java");

                long c=dataSnapshot.getChildrenCount();
                System.out.println("count:"+c);

                for(DataSnapshot itm:items)
                {
                    map = (HashMap<String, Object>) itm.getValue();


                    if((map!=null))
                    {
                        String mapdata=map.get("data").toString();
                        System.out.println("mapdata:"+mapdata);
                       // System.out.println("reciever_name,current_user_name:"+reciever_name+","+current_user_name);

                        String dataarray[] = mapdata.split("_");
                        String messageS = dataarray[0];
                        if (dataarray[1].equals(current_user_name)&&dataarray[2].equals(reciever_name))
                        {
                            //msg was sent by you
                            addMessageBox(messageS,1);
                        }
                        if (dataarray[1].equals(reciever_name)&&dataarray[2].equals(current_user_name))
                        {
                            //msg was sent by other person
                            addMessageBox(messageS,2);
                        }
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        /*myRef.child(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                map = (HashMap<String, Object>) dataSnapshot.getValue();
                if(map!=null)
                {
                    String message = map.get("message").toString();
                    String userName = map.get("reciever").toString();

                    if(userName.equals(UserDetails.username)){
                        addMessageBox("You:-\n" + message, 1);
                    }
                    else{
                        addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                    }
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
        });*/


    }
    public void addMessageBox(String message, int type){
        TextView textView = new TextView(Reply.this);
        textView.setText(message);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);


        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(4,4,4,4);
        int width=layout.getWidth();
        System.out.println("width of layout:"+width);
        textView.setMaxWidth(width*5/6);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.round_corner1);
            textView.setTextColor(Color.parseColor("#ffffff"));
        }
        else{
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.round_corner2);
            textView.setTextColor(Color.parseColor("#ffffff"));
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
        scrollView.setVerticalScrollBarEnabled(false);
    }
}
