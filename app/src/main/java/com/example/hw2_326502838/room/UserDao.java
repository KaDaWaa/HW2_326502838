package com.example.hw2_326502838.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();
    @Query("SELECT * FROM users WHERE uid =:id")
    User getStudentById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    @Delete
    void deleteStudent(User user);

}
