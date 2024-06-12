package com.example.hw2_326502838.recyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hw2_326502838.R;
import com.example.hw2_326502838.databinding.RvUserItemBinding;
import com.example.hw2_326502838.room.User;


public class UserViewHolder extends RecyclerView.ViewHolder {
    RvUserItemBinding binding;
    public UserViewHolder(@NonNull RvUserItemBinding binding){
        super(binding.getRoot());
        this.binding=binding;
    }
    public void bind(User user){
        Glide.with(binding.getRoot())
                .load(user.picture)
                .error(R.drawable.error)
                .placeholder(R.drawable.idpic)
                .into(binding.rvUserImg);
        binding.textViewUserFname.setText(user.firstName);
        binding.textViewUserLname.setText(user.lastName);
        binding.textViewUserCountry.setText(user.country);
        binding.textViewUserCity.setText(user.city);



    }
}
