package com.example.hw2_326502838.user;

public class User {
    public String id;
    public String firstname;
    public String lastname;
    public String email;
    public double age;
    public String city;
    public String country;
    public String picture;

    public User(String uid, String firstname, String lastname, String email, double age, String city, String country, String picture) {
        this.id = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.city = city;
        this.country = country;
        this.picture = picture;
    }



}