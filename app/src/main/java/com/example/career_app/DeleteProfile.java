package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);
        ButterKnife.bind(this);
    }


    //Navigation buttons
    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton)
    void accountButton(View view) {
        Toast.makeText(this, "Welcome to Manage Profile!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, manageProfile.class);
        startActivity(intent);
    }

    //Search Careers Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.careersSearch)
    void careersSearch(View view) {
        Toast.makeText(this, "Welcome to Careers!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CareersActivity.class);
        startActivity(intent);
    }

    //Home Buttons
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.homeButton)
    void homeButton(View view) {
        Toast.makeText(this, "Welcome to the Home!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}