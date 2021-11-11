package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.careersSearch) void careersSearch(View view){
        Toast.makeText(this, "careers!", Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton) void clickAccount(View view){
        Toast.makeText(this, "accounts!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }
}