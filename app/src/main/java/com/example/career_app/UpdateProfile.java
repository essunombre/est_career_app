package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;

    private Button updateUser;

    private EditText changeName,
            changeEmail,
            changePassword,
            verifyPassword2,
            changeCareer,
            changeExtras,
            changePhoneNumber;

    /*public UpdateProfile(DatabaseReference database) {
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]
    }
    //mDatabase = FirebaseDatabase.getInstance().getReference();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);

        updateUser = (Button) findViewById(R.id.updateUser);
        updateUser.setOnClickListener((View.OnClickListener) this);
        changeName = (EditText) findViewById(R.id.changeName);
        changeEmail = (EditText) findViewById(R.id.changeEmail);
        changePassword = (EditText) findViewById(R.id.changePassword);
        verifyPassword2 = (EditText) findViewById(R.id.verifyPassword2);
        changeCareer = (EditText) findViewById(R.id.changeCareer);
        changeExtras = (EditText) findViewById(R.id.changeExtras);
        changePhoneNumber = (EditText) findViewById(R.id.changePhoneNumber);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void updateUser(String userId, String name, String email, String phone, String extras, String career) {
        User user = new User(name, email, phone, extras, career);

        mDatabase.child("users").child(userId).child("username").setValue(name);
        mDatabase.child("users").child(userId).child("username").setValue(email);
        mDatabase.child("users").child(userId).child("username").setValue(phone);
        mDatabase.child("users").child(userId).child("username").setValue(extras);
        mDatabase.child("users").child(userId).child("username").setValue(career);
    }
    /*updateUser.setOnClickListener(View.OnClickListener()) {
        public void onClock(View view) {
                Map<String, Object> personMap = new HashMap<>() {
                    mDatabase.child("users").child(userId).child("username").setValue(name);
        mDatabase.child("users").child(userId).child("username").setValue(email);
        mDatabase.child("users").child(userId).child("username").setValue(phone);
        mDatabase.child("users").child(userId).child("username").setValue(extras);
        mDatabase.child("users").child(userId).child("username").setValue(career);
                }
        }
    }*/
   /* private void updateUser() {
    mDatabase.child("User").updateChildren(name);
    mDatabase.child("User").updateChildren(email);
    mDatabase.child("User").updateChildren(phone);
    mDatabase.child("User").updateChildren(extras);
    mDatabase.child("User").updateChildren(career);
    }*/


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.updateUser) void updateUser(){
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_LONG).show();
    }
    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton) void accountButton(View view){
        Toast.makeText(this, "You are in the Account Page Already!", Toast.LENGTH_LONG).show();
    }
    //Search Careers Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.careersSearch) void careersSearch(View view){
        Toast.makeText(this, "Welcome to the Careers Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CareersActivity.class);
        startActivity(intent);
    }
    //Home Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.homeButton) void homeButton(View view){
        Toast.makeText(this, "Welcome to the Home Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateUser:
                updateUser();
                break;

        }
    }
    /*public void updateUser(String name, String email, String phone, String extras, String career) {
        User user = new User(name, email, phone, extras, career);

        mDatabase.child("user").updateChildren(name);
        mDatabase.child("User").updateChildren(email);
        mDatabase.child("User").updateChildren(phone);
        mDatabase.child("User").updateChildren(extras);
        mDatabase.child("User").updateChildren(career);
    }*/
//    private void updateUser() {
//        User user = new User(name, email, phone, extras, career);
//
//        mDatabase.child("user").updateChildren(thiname);
//        mDatabase.child("User").updateChildren(email);
//        mDatabase.child("User").updateChildren(phone);
//        mDatabase.child("User").updateChildren(extras);
//        mDatabase.child("User").updateChildren(career);
//    }
}