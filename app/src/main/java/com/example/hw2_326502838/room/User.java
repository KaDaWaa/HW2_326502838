package com.example.hw2_326502838.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users",indices = {@Index(value = {"uid"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cnt")
    public int cnt;
    @ColumnInfo(name = "uid")
    public String uid;

    @ColumnInfo(name = "picture")
    public String picture;
    @ColumnInfo(name = "first_Name")
    public String firstName;
    @ColumnInfo(name = "last_Name")
    public String lastName;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "country")
    public String country;



}
