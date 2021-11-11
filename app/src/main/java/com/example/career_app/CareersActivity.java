package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CareersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careers);
        ButterKnife.bind(this);
    }

    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton) void clickAccount(View view){
        Toast.makeText(this, "Welcome to the accounts!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }
    //Search Careers Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.careersSearch) void careersSearch(View view){
        Toast.makeText(this, "You are in the Careers Page already!", Toast.LENGTH_LONG).show();
    }
    //Home Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.homeButton) void homeButton(View view){
        Toast.makeText(this, "Welcome to the Home Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}