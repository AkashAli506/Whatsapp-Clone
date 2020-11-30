package com.akashali.i170019_i170282;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.MyViewHolder> {
    List<MyAppContact> newList;
    Context c;
    private MyRvAdapter.OnItemClickListener mListener;
    public UserSearchAdapter(List<MyAppContact> newList, Context c) {
        this.c=c;
        this.newList=newList;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(MyRvAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public UserSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.user_row,parent,false);
        return new UserSearchAdapter.MyViewHolder(itemrow,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSearchAdapter.MyViewHolder holder, int position) {
        holder.username.setText(newList.get(position).getName());
        holder.userage.setText(newList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView userage;
        public MyViewHolder(@NonNull View itemView, final MyRvAdapter.OnItemClickListener listener) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            userage=itemView.findViewById(R.id.userage);
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
