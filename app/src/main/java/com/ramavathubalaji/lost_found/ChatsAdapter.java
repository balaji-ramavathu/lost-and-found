package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder>
{
    ArrayList<String> reciever=new ArrayList<String>();
    Context context;

    public  ChatsAdapter(Context context,ArrayList<String> reciever)
    {
        this.context=context;
        this.reciever=reciever;

    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvChatitem;

        public ViewHolder(View v) {
            super(v);

            tvChatitem=(TextView) v.findViewById(R.id.tv_chatitem);

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chats_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvChatitem.setText(reciever.get(position));

        for(int i=0;i<reciever.size();i++)
        {
            System.out.println("onBindViewholder reciever:"+reciever.get(i));
        }
        System.out.println("onBindViewHolder position:"+position+" reciver.get(pos):"+reciever.get(position));

    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount:"+reciever.size());
        return reciever.size();

    }




}

