package com.example.career_app;
public class Career {
    private String career;
    private String email;
    private String extras;
    private String name;
    private String phone;

    public Career(){

    }

//    public Career(String email, String name, String phone, String career, String extras){
//        this.email = email;
//        this.name = name;
//        this.phone = phone;
//        this.career = career;
//        this.extras = extras;
//    }


    public Career(String career, String email, String extras, String name, String phone) {
        this.career = career;
        this.email = email;
        this.extras = extras;
        this.name = name;
        this.phone = phone;
    }



    public String get() {
        String result = "";
        if (!this.career.isEmpty()) {
            result += this.career + " ";
        }
        if (!this.email.isEmpty()) {
            result += this.email + " ";
        }
        if (!this.name.isEmpty()) {
            result += this.name + " ";
        }
        if (!this.extras.isEmpty()) {
            result += this.extras + " ";
        }
        return result;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
