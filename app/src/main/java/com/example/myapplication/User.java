package com.example.myapplication;

public class User {
    public static final int TYPE_JOB_SEEKER = 0;
    public static final int TYPE_RECRUITER = 1;
    public static final int TYPE_ADMIN = 2;

    public String username;
    public String password;
    public int userType;

    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}