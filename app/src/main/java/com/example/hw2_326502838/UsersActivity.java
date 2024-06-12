package com.example.hw2_326502838;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2_326502838.databinding.ActivityMainBinding;
import com.example.hw2_326502838.databinding.ActivityUsersBinding;
import com.example.hw2_326502838.recyclerView.UserRecyclerViewAdapter;
import com.example.hw2_326502838.room.UserDB;
import com.example.hw2_326502838.room.User;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    ActivityUsersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding= ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView=binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        );
        UserDB db=UserDB.getInstance(this);
        List<User> users=db.userDao().getAll();
        UserRecyclerViewAdapter recyclerViewAdapter = new UserRecyclerViewAdapter(users);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}