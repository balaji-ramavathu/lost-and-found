package com.ramavathubalaji.lost_found;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourLostFragment extends Fragment
{
    View v;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter, m2Adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> namel=new ArrayList<String>();
    private ArrayList<String> timel=new ArrayList<String>();
    private ArrayList<String> titlel=new ArrayList<String>();
    private ArrayList<String> descriptionl=new ArrayList<String>();
    private ArrayList<String> lfl=new ArrayList<String>();
    HashMap<String, Object> user;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    String email=firebaseAuth.getCurrentUser().getEmail().toString();


    public YourLostFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        System.out.println("entered onCreateView lost fragment");
        v = inflater.inflate(R.layout.content_main22, container, false);
        mRecyclerView=(RecyclerView) v.findViewById(R.id.my_recycler_view22);

       /* titlel.clear();
        descriptionl.clear();
        timel.clear();
        namel.clear();
        lfl.clear();*/


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        try
        {
            final DatabaseReference myRef = database.getReference().child("lost");
            myRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {

                    Iterable<DataSnapshot> items=dataSnapshot.getChildren();
                    System.out.println("into lost ondatachange,items:"+items);

                    long c=dataSnapshot.getChildrenCount();
                    System.out.println("count:"+c);



                    if(titlel.isEmpty()&&descriptionl.isEmpty()&&timel.isEmpty()&&namel.isEmpty()&&lfl.isEmpty())
                    {
                        for(DataSnapshot itm:items)
                        {
                            user = (HashMap<String, Object>) itm.getValue();
                            if(user!=null)
                            {
                                String tempp = user.get("data").toString();
                                System.out.println("tempp: " + tempp);
                                //String name[]=new String[4];
                                if (tempp.length() > 10)
                                {
                                    String dataarray[] = tempp.split("_");
                                    if (dataarray[4].equals("lost")&&dataarray[3].equals(email))
                                    {
                                        titlel.add(dataarray[0]);
                                        descriptionl.add(dataarray[1]);
                                        timel.add(dataarray[2]);
                                        namel.add(dataarray[3]);
                                        lfl.add(dataarray[4]);


                                    }
                                }
                            }

                        }
                    }
                    else
                    {
                        titlel.clear();
                        descriptionl.clear();
                        timel.clear();
                        namel.clear();
                        lfl.clear();
                        for(DataSnapshot itm:items)
                        {
                            user = (HashMap<String, Object>) itm.getValue();
                            if(user!=null)
                            {
                                String tempp = user.get("data").toString();
                                System.out.println("tempp: " + tempp);
                                //String name[]=new String[4];
                                if (tempp.length() > 10)
                                {
                                    String dataarray[] = tempp.split("_");
                                    if (dataarray[4].equals("lost")&&dataarray[3].equals(email))
                                    {
                                        titlel.add(dataarray[0]);
                                        descriptionl.add(dataarray[1]);
                                        timel.add(dataarray[2]);
                                        namel.add(dataarray[3]);
                                        lfl.add(dataarray[4]);


                                    }
                                }
                            }

                        }
                    }
                    for(int i=0;i<titlel.size();i++)
                    {
                        System.out.println("titlel from yourlostfragment:"+" "+i+titlel.get(i));
                    }

                    mAdapter = new YourAdapter(getContext(),namel,timel,titlel,descriptionl,lfl);
                    //mRecyclerView.setHasFixedSize(true);

                    // use a linear layout manager
                    //mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
        catch (Exception e)
        {

        }
        if(mAdapter!=null)
        {
            mAdapter.notifyDataSetChanged();
        }




        /*mAdapter = new MyAdapter(getContext(),namel,timel,titlel,descriptionl,lfl);
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);*/
        return v;
    }
}
