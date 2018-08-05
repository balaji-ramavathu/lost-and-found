package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by RAMAVATHU BALAJI on 18-05-2018.
 */



public class YourAdapter extends RecyclerView.Adapter<YourAdapter.ViewHolder>
{
    int i,count,p;


    private ArrayList<String> namel=new ArrayList<String>();
    private ArrayList<String> timel=new ArrayList<String>();
    private ArrayList<String> titlel=new ArrayList<String>();
    private ArrayList<String> descriptionl=new ArrayList<String>();
    private ArrayList<String> lfl=new ArrayList<String>();
    Context mcontext;
    String key,data,titleStr,descriptionStr,nameStr,lfStr;


    public YourAdapter(Context context,
                       ArrayList<String> namel,
                     ArrayList<String> timel,
                     ArrayList<String> titlel,ArrayList<String> descriptionl,ArrayList<String> lfl )
    {
        System.out.println("youradapter constructor (suspect101)entered");
        mcontext=context;
        this.namel=namel;
        this.timel=timel;
        this.titlel=titlel;
        this.descriptionl=descriptionl;
        this.lfl=lfl;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtitle,tvdescription,tvtime;
        Button deletebtn, editbtn;




        public ViewHolder(View v)
        {
            super(v);

            tvtitle=(TextView) v.findViewById(R.id.tvtitle);
            tvdescription=(TextView) v.findViewById(R.id.tvdescription);
            //tvtime=(TextView) v.findViewById(R.id.your_tvtime);
            deletebtn=(Button) v.findViewById(R.id.your_btndelete);
            editbtn=(Button) v.findViewById(R.id.your_btnedit);

            /*deletebtn.setOnClickListener(this);*/

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }

        /*@Override
        public void onClick(View v) {

            if(v.equals(deletebtn))
            {
                int position=getAdapterPosition();
                data=titlel.get(position)+"_"+descriptionl.get(position)+"_"+timel.get(position)+"_"+namel.get(position)+"_"+lfl.get(position);
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("lost").orderByChild("data");

                userQuery.equalTo(data).addListenerForSingleValueEvent(
                        new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot foodSnapshot: dataSnapshot.getChildren())
                                {
                                    // result
                                    key = foodSnapshot.getKey();
                                    System.out.println("key got:"+key);
                                    *//*titlel.remove(position);
                                    descriptionl.remove(position);
                                    namel.remove(position);
                                    lfl.remove(position);
                                    timel.remove(position);*//*
                                    foodSnapshot.getRef().removeValue();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
                removeAt(getAdapterPosition());


            }*//*else if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }*//*
        }
        public void removeAt(int position) {
            titlel.remove(position);
            descriptionl.remove(position);
            namel.remove(position);
            lfl.remove(position);
            timel.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(position,1);

        }*/


    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_post_card, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        System.out.println("entered onbindviewholder in myadapter");

        /*data=titlel.get(position)+"_"+descriptionl.get(position)+"_"+timel.get(position)+"_"+namel.get(position)+"_"+lfl.get(position);

        System.out.println("data:"+data);*/
        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                data=titlel.get(position)+"_"+descriptionl.get(position)+"_"+timel.get(position)+"_"+namel.get(position)+"_"+lfl.get(position);

                System.out.println("data:"+data);
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("lost").orderByChild("data");

                userQuery.equalTo(data).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                                    // result
                                    key = snap.getKey();
                                    System.out.println("key got:"+key);
                                    titleStr=titlel.get(position);
                                    descriptionStr=descriptionl.get(position);
                                    nameStr=namel.get(position);
                                    lfStr=lfl.get(position);
                                    System.out.println("title,descr,key:"+titleStr+","+descriptionStr+","+key);

                                    YourPosts.INSTANCE.finish();
                                    Intent i = new Intent(YourPosts.INSTANCE, EditPost.class);
                                    i.putExtra("title",titleStr);
                                    i.putExtra("description",descriptionStr);
                                    i.putExtra("key",key);
                                    i.putExtra("lf",lfStr);
                                    i.putExtra("name",nameStr);
                                    YourPosts.INSTANCE.startActivity(i);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );

            }
        });

       // holder.deletebtn.setOnClickListener(delete(holder.getAdapterPosition()));

        holder.deletebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*Global g=Global.getInstance();
                g.setCount(1);*/


                /*int position=holder.getAdapterPosition();*/
                data=titlel.get(position)+"_"+descriptionl.get(position)+"_"+timel.get(position)+"_"+namel.get(position)+"_"+lfl.get(position);
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("lost").orderByChild("data");



                userQuery.equalTo(data).addListenerForSingleValueEvent(
                        new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot foodSnapshot: dataSnapshot.getChildren())
                                {
                                    // result
                                    key = foodSnapshot.getKey();
                                    System.out.println("key got:"+key);
                                    foodSnapshot.getRef().removeValue();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
                titlel.remove(position);
                descriptionl.remove(position);
                namel.remove(position);
                lfl.remove(position);
                timel.remove(position);
                for(int i=0;i<titlel.size();i++)
                {
                    System.out.println("araylist:"+i+" "+titlel.get(i));
                }
                notifyItemRemoved(position);
                System.out.println("notified item removed");

            }
        });

        //System.out.println("all are not null");
        //holder.tvtime.setText(timel.get(position));
        /*Global g=Global.getInstance();
        p=g.getCount();
        if(p==1)
        {
            System.out.println("if cond p==1 entered yoyoyoyoyoo");
            ArrayList<String> tmp=new ArrayList<String>();
            for (int i = 0 ; i<titlel.size();i++){
                tmp.add(titlel.get(i)) ;                //copied from titlel
            }
            for(i=0;i<tmp.size()/2;i++)
            {
                titlel.clear();
                titlel.add(tmp.get(i));                 //cleared titlel and copied half of tmp
            }
            tmp.clear();
            for (int i = 0 ; i<descriptionl.size();i++){
                tmp.add(descriptionl.get(i)) ;              //cleared tmp and copied from descriptionl
            }
            for(i=0;i<tmp.size()/2;i++)
            {
                descriptionl.clear();
                descriptionl.add(tmp.get(i));               //cleared desc and copied half from tmp
            }
            tmp.clear();
            p=0;
            g.setCount(0);
        }
        for(int i=0;i<titlel.size();i++)
        {
            System.out.println("araylist outside del:"+i+" "+titlel.get(i));
        }*/

        holder.tvtitle.setText(titlel.get(position));
        holder.tvdescription.setText(descriptionl.get(position));
    }





    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {

        count=descriptionl.size();
        System.out.println("count from youradapter getitemcount:"+count);

        return count;

    }
}


