package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.email) TextView email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.password) TextView password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.create_account) TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.login) void loginButton(View view ){
        Toast.makeText(this,email.getText() + " " + password.getText(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.create_account) void createAccount(View view ){
        Toast.makeText(this,email.getText() + " " + password.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

}