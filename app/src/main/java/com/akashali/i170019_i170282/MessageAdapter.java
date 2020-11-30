package com.akashali.i170019_i170282;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    List<Chat> newList;
    Context c;
    public MessageAdapter(List<Chat> newList, Context c) {
        this.c=c;
        this.newList=newList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==MSG_TYPE_RIGHT) {
            View itemrow = LayoutInflater.from(c).inflate(R.layout.chat_item_right, parent, false);
            return new MyViewHolder(itemrow);
        }
        else {
            View itemrow = LayoutInflater.from(c).inflate(R.layout.chat_item_left, parent, false);
            return new MyViewHolder(itemrow);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Chat chat=newList.get(position);
        holder.show_message.setText(chat.getMessage());
        holder.receiver_image.setImageBitmap(chat.getImg());
        if(newList.get(holder.getAdapterPosition()).getmUriList().isEmpty()){
            holder.viewmedia.setVisibility(View.GONE);
        }
        holder.viewmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // new ImageVieweuilder(v.getContext(), newList.get(holder.getAdapterPosition()).getmUriList())
                //        .setStartPosition(0)
                  //      .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView receiver_image;
        public TextView show_message;
        public Button viewmedia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            receiver_image=itemView.findViewById(R.id.image);
            show_message=itemView.findViewById(R.id.show_message);
            viewmedia=itemView.findViewById(R.id.media);
        }
    }

    /*public int getItemViewType(int position)
    {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if(newList.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }*/
}
