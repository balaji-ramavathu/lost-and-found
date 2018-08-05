package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RAMAVATHU BALAJI on 24-05-2018.
 */

public class My2Adapter extends RecyclerView.Adapter<My2Adapter.ViewHolder>
{

    private String[] names=new String[100];/*={"balaji ramavathu","tejaswi ramavathu","sandhya ramavathu","radha ramavathu"};*/
    private String[] times=new String[100];/*={"12:50","02:05","03:56","04:23"};*/
    private String[] titles=new String[100];/*={"Lost ID","Lost Purse","Lost earphones","Lost comb"};*/
    private String[] descriptions=new String[100];
    private String[] lfs=new String[100];
    String titleS,nameS,emailS;
    int i,countf,lposts,fposts;
    private FirebaseAuth firebaseAuth;
    HashMap<String,Object> user;
    SharedPreferences sharedPreferences;
    Context mcontext;
    private ArrayList<String> namef=new ArrayList<String>();
    private ArrayList<String> timef=new ArrayList<String>();
    private ArrayList<String> titlef=new ArrayList<String>();
    private ArrayList<String> descriptionf=new ArrayList<String>();
    private ArrayList<String> lff=new ArrayList<String>();

    public My2Adapter(Context context, ArrayList<String> namef, ArrayList<String> timef,
                     ArrayList<String> titlef, ArrayList<String> descriptionf, ArrayList<String> lff )
    {
        mcontext=context;
        this.namef=namef;
        this.timef=timef;
        this.titlef=titlef;
        this.descriptionf=descriptionf;
        this.lff=lff;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvname,tvtitle,tvdescription,tvtime;


        public ViewHolder(View v) {
            super(v);
            tvname=(TextView) v.findViewById(R.id.tvname);
            tvtitle=(TextView) v.findViewById(R.id.tvtitle);
            tvdescription=(TextView) v.findViewById(R.id.tvdescription);
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        System.out.println("entered onbindviewholder in my2adapter");

        holder.tvtime.setText(timef.get(position));
        holder.tvname.setText(namef.get(position));
        holder.tvtitle.setText(titlef.get(position));
        holder.tvdescription.setText(descriptionf.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        countf=descriptionf.size();
        System.out.println("countf from my2adapter getitemcount:"+countf);

        /*Global b = Global.getInstance();
        int g=b.getlposts();

        sharedPreferences = Main22Activity.INSTANCE.getSharedPreferences("shared", Context.MODE_PRIVATE);
        countf=sharedPreferences.getInt("countf",0);
        System.out.println("countf from getitemcount:"+countf);*/
        return countf;


    }

}
