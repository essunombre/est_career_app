package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private static final String TAG = "ForgotPassword";

    private EditText editTextEmail;
    private Button buttonReset;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = (EditText) findViewById(R.id.editForgetEmail);
        buttonReset = (Button)  findViewById(R.id.button_reset);

        Auth = FirebaseAuth.getInstance();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
        String email = editTextEmail.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Provide a Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Email sent, please check your email",Toast.LENGTH_LONG).show();
                    Log.i(TAG, "Email sent to: " + email);
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                    startActivity(new Intent(this,MainActivity. class));
                }else{
                    Toast.makeText(ForgotPassword.this,"Email is not registered",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Email : " + email +" was not found.");
                }
            }
        });

    }
}