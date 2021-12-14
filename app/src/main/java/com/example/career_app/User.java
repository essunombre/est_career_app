package com.example.career_app;

// this class is used to create the user using the firebase functions, in this way we create different inputs for the registration
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

