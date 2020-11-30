package com.akashali.i170019_i170282;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {

    List<ChatObject> newList;
    Context c;
    private OnItemClickListener mListener;
    public MyRvAdapter(List<ChatObject> newList, Context c) {
        this.c=c;
        this.newList=newList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemrow,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.newimage.setImageBitmap(newList.get(position).getImage());
        holder.name.setText(newList.get(position).getName());
        holder.message.setText(newList.get(position).getMessage());
        holder.time.setText(newList.get(position).getTime());
        holder.messagecount.setText(newList.get(position).getMessageCount());
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView newimage;
        public TextView name;
        public TextView message;
        public TextView time;
        public TextView messagecount;
        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            newimage=itemView.findViewById(R.id.newimage);
            name=itemView.findViewById(R.id.personname);
            message=itemView.findViewById(R.id.message);
            time=itemView.findViewById(R.id.time);
            messagecount=itemView.findViewById(R.id.messagecount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
