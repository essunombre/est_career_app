package com.example.career_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private TextView forgotPassword;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.create_account) TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        forgotPassword = (TextView) findViewById((R.id.forgotPassword));
        forgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }

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