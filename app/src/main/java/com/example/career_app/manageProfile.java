package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class manageProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);
        ButterKnife.bind(this);
    }

    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton) void clickAccount(View view){
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
    //Update Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.update_profile) void updateProfile(View view){
        Toast.makeText(this, "Welcome to the Update Profile Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }
    //Delete Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.delete_profile) void deleteProfile(View view){
        Toast.makeText(this, "you are about to delete your profile!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DeleteProfile.class);
        startActivity(intent);
    }
    //Log out Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.log_out_profile) void logOutProfile(View view){
        Toast.makeText(this, "log out successful!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}