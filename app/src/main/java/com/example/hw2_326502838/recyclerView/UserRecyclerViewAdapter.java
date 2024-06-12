package com.example.hw2_326502838.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2_326502838.R;
import com.example.hw2_326502838.databinding.RvUserItemBinding;
import com.example.hw2_326502838.room.User;
import com.example.hw2_326502838.room.UserDB;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerViewAdapter  extends RecyclerView.Adapter<UserViewHolder> {
    private final List<User> dataSet;
    RvUserItemBinding binding;
    UserDB db;
    public UserRecyclerViewAdapter(List<User> dataSet){
        this.dataSet=dataSet;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        binding=RvUserItemBinding.inflate(inflater,parent,false);

            db = UserDB.getInstance(parent.getContext());

        return new UserViewHolder(binding);
    }
     public void onBindViewHolder(@NonNull UserViewHolder holder,int position){
        holder.bind(dataSet.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            final int adapterPosition=holder.getAdapterPosition();
            @Override
            public boolean onLongClick(View v) {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    try {
                        db.userDao().deleteStudent(dataSet.get(adapterPosition));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    dataSet.remove(adapterPosition);
                    notifyDataSetChanged();
                }
                return true;
            }
        });
     }


     @Override
    public int getItemCount(){
        return dataSet.size();
     }
}
