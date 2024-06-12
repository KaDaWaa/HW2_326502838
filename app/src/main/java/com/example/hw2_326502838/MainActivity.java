package com.example.hw2_326502838;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.hw2_326502838.UserServiceAndApi.UserAPIClient;
import com.example.hw2_326502838.UserServiceAndApi.UserService;
import com.example.hw2_326502838.databinding.ActivityMainBinding;
import com.example.hw2_326502838.room.UserDB;
import com.example.hw2_326502838.user.Result;
import com.example.hw2_326502838.user.Root;
import com.example.hw2_326502838.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    final String load="loading...";
    final String err="ERROR";
    boolean isError=true;//you cant add user to collection when first starting the app.
    User currentUser;
    UserDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=UserDB.getInstance(this);
        binding.buttonSeeNext.setOnClickListener(onClickListenerGetNextUser);
        binding.buttonAddToCollection.setOnClickListener(onClickListenerAddUser);
        binding.buttonViewCollection.setOnClickListener(onClickListenerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextUser();
    }

    View.OnClickListener onClickListenerGetNextUser= v ->
        nextUser();


    private void nextUser(){
        Retrofit retrofit= UserAPIClient.getClient();
        UserService service=retrofit.create(UserService.class);
        Call<Root> callAsync=service.getUsers(null,null,null);
        binding.textViewFirstName.setText(load);
        binding.textViewLastName.setText(load);
        binding.textViewAge.setText(load);
        binding.textViewCity.setText(load);
        binding.textViewCountry.setText(load);
        binding.textViewEmail.setText(load);
        binding.buttonAddToCollection.setEnabled(false);
        binding.buttonSeeNext.setEnabled(false);
        binding.buttonViewCollection.setEnabled(false);
        callAsync.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                Root root=response.body();
                assert root!=null;
                Result r= root.results.get(0);
                Glide.with(binding.getRoot())
                        .load(r.picture.large)
                        .placeholder(R.drawable.idpic)
                        .error(R.drawable.error)
                        .into(binding.imageView);
                binding.textViewFirstName.setText(r.name.first);
                binding.textViewLastName.setText(r.name.last);
                binding.textViewAge.setText(String.valueOf(r.dob.age));
                binding.textViewCity.setText(r.location.city);
                binding.textViewCountry.setText(r.location.country);
                binding.textViewEmail.setText(r.email);
                isError=false;
                binding.buttonAddToCollection.setEnabled(true);
                binding.buttonSeeNext.setEnabled(true);
                binding.buttonViewCollection.setEnabled(true);
                currentUser=new User(r.login.username,r.name.first,r.name.last,r.email,r.dob.age,r.location.city,r.location.country,r.picture.large);
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable throwable) {
                isError=true;
                binding.textViewFirstName.setText(err);
                binding.textViewLastName.setText(err);
                binding.textViewAge.setText(err);
                binding.textViewCity.setText(err);
                binding.textViewCountry.setText(err);
                binding.textViewEmail.setText(err);
                Glide.with(binding.getRoot()).load(R.drawable.error).error(R.drawable.error).into(binding.imageView);
                binding.buttonAddToCollection.setEnabled(true);
                binding.buttonSeeNext.setEnabled(true);
                binding.buttonViewCollection.setEnabled(true);
            }
        });
    }

    View.OnClickListener onClickListenerAddUser=v->{
        if(isError){
            Toast.makeText(this,"Cannot add user to collection at the moment",Toast.LENGTH_LONG).show();
        }
        else {
            com.example.hw2_326502838.room.User user=new com.example.hw2_326502838.room.User();
            user.uid=currentUser.id;
            user.firstName=currentUser.firstname;
            user.lastName=currentUser.lastname;
            user.city=currentUser.city;
            user.country=currentUser.country;
            user.picture=currentUser.picture;
            db.userDao().insertUser(user);
        }
    };

    View.OnClickListener onClickListenerView = v -> {
        Intent intent=new Intent(this,UsersActivity.class);
        startActivity(intent);
    };

}