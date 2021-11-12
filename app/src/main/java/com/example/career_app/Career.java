package com.example.career_app;

public class Career {
    private String career;
    private String email;
    private String extras;
    private String name;
    private int phone;

    public Career(){

    }

    public Career(String career, String email, String extras, String name, int phone) {
        this.career = career;
        this.email = email;
        this.extras = extras;
        this.name = name;
        this.phone = phone;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


}
