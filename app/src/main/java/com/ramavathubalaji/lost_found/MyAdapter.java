package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;




/**
 * Created by RAMAVATHU BALAJI on 18-05-2018.
 */





public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] names=new String[100];/*={"balaji ramavathu","tejaswi ramavathu","sandhya ramavathu","radha ramavathu"};*/
    private String[] times=new String[100];/*={"12:50","02:05","03:56","04:23"};*/
    private String[] titles=new String[100];/*={"Lost ID","Lost Purse","Lost earphones","Lost comb"};*/
    private String[] descriptions=new String[100];/*={"I have lost my ID card. If anyone found it please reply.",
            "I have lost my Purse. If anyone found it please reply.",
            "I have lost my Skull Candy Earphones. If anyone found it please reply.",
            "I have lost my red comb. If anyone found it please reply."};*/
    private String[] lfs=new String[100];
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String titleS,nameS,emailS;
    int i,count,lposts,fposts,dcount;
    long t;
    HashMap<String,Object> user;
    HashMap<String,Object> hashMap;
    SharedPreferences sharedPreferences;
    private ArrayList<String> namel=new ArrayList<String>();
    private ArrayList<String> timel=new ArrayList<String>();
    private ArrayList<String> titlel=new ArrayList<String>();
    private ArrayList<String> descriptionl=new ArrayList<String>();
    private ArrayList<String> lfl=new ArrayList<String>();
    Context mcontext;


    public MyAdapter(Context context,
                     ArrayList<String> namel,ArrayList<String> timel,
                     ArrayList<String> titlel,ArrayList<String> descriptionl,ArrayList<String> lfl )
    {
        this.mcontext=context;
        this.namel=namel;
        this.timel=timel;
        this.titlel=titlel;
        this.descriptionl=descriptionl;
        this.lfl=lfl;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvname,tvtitle,tvdescription,tvtime;
        public Button btnreply;


        public ViewHolder(View v) {
            super(v);
             tvname=(TextView) v.findViewById(R.id.tvname);
            tvtitle=(TextView) v.findViewById(R.id.tvtitle);
            tvdescription=(TextView) v.findViewById(R.id.tvdescription);
            btnreply=(Button) v.findViewById(R.id.btnreply);
            /*tvtime=(TextView) v.findViewById(R.id.tvtime);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }


        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position)
        {
            System.out.println("entered onbindviewholder in myadapter");

            //System.out.println("all are not null");
            /*holder.tvtime.setText(timel.get(position));*/



            holder.btnreply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getAdapterPosition();
                    String name=namel.get(pos);
                    System.out.println("username from myadapter: "+name);
                    Intent i=new Intent(Main22Activity.INSTANCE,Reply.class);
                    i.putExtra("username",name);
                    Main22Activity.INSTANCE.startActivity(i);

                }
            });


            holder.tvname.setText(namel.get(position));
            holder.tvtitle.setText(titlel.get(position));
            holder.tvdescription.setText(descriptionl.get(position));

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount()
        {


            count=descriptionl.size();
            System.out.println("count from myadapter getitemcount:"+count);


            return count;



        }
    }


