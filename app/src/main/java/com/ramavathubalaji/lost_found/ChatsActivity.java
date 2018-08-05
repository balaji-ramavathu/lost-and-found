package com.ramavathubalaji.lost_found;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static java.security.AccessController.getContext;

public class ChatsActivity extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> reciever=new ArrayList<String>();
    HashMap<String,Object> map;
    String current_user_name,reciever_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        mRecyclerView=(RecyclerView) findViewById(R.id.rvchats);



        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        final String current_user_email=firebaseAuth.getCurrentUser().getEmail().toString();
        String[] split_array=current_user_email.split("@");
        current_user_name=split_array[0];
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference=database.getReference().child("messages");
        userReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                map = (HashMap<String, Object>) dataSnapshot.getValue();
                if((map!=null))
                {
                    String mapdata=map.get("data").toString();
                    System.out.println("inoto onchildadded map!=nll");
                    System.out.println("mapdata:"+mapdata);

                    String dataarray[] = mapdata.split("_");
                    String messageS = dataarray[0];
                    if (dataarray[1].equals(current_user_name))
                    {
                        System.out.println("into dataarray[1].equals(current_username)");
                        //msg was sent by you
                        if(reciever.isEmpty())
                        {
                            System.out.println("into reciever.isempty()");
                            reciever.add(dataarray[2]);
                            System.out.println("added to reciever:"+dataarray[2]);

                        }
                        else
                        {
                            int p=1;
                            String temp=dataarray[2];
                            System.out.println("temp:daataarray[2]:"+temp);
                            Iterator<String> iterator=reciever.iterator();
                            while(iterator.hasNext())
                            {
                                String next=iterator.next();
                                System.out.println("reciever arraylit :"+next);
                                if(next!=null)
                                {
                                    if(next.toString().equals(temp))
                                    {
                                        p=0;
                                        break;
                                    }
                                }
                            }
                            if(p==1)
                            {
                                reciever.add(temp);
                                System.out.println("added to reciever:"+temp);
                                for(int i=0;i<reciever.size();i++)
                                {
                                    System.out.println("arraylist reciever before setting adapter:"+reciever.get(i));
                                }

                            }
                        }
                        mAdapter = new ChatsAdapter(getApplicationContext(),reciever);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecyclerView.setAdapter(mAdapter);
                        System.out.println("madapter set life");
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

        /*mAdapter = new ChatsAdapter(this,reciever);
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        System.out.println("madapter set life");*/

    }


}
