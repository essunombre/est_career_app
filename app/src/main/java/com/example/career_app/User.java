package com.example.career_app;


public class User {
    public String name, email, phone, extras, career, password, confirmPassword;

    public User(){

    }
    public User(String name, String email, String phone, String extras, String career, String password, String confirmPassword){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.extras = extras;
        this.career = career;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}

