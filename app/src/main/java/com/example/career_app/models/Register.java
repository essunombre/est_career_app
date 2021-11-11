package com.example.career_app.models;

import com.orm.SugarRecord;

public class Register extends SugarRecord<Register> {
    String email;
    String password;
    String Extras;
    int phone_number;

    public Register(){
    }

    public Register(String email, String password, String extras, int phone_number) {
        this.email = email;
        this.password = password;
        Extras = extras;
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExtras() {
        return Extras;
    }

    public void setExtras(String extras) {
        Extras = extras;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
