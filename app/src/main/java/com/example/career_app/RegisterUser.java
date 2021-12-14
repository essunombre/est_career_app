package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterUser";
    private TextView registerUser;

    private EditText editTextName,
            editTextEmail,
            editTextPassword,
            editTextVerifyPassword,
            editTextExtras,
            editTextPhone;

    //Array adapter to create the dropdown
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterCareers;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterCareers = new ArrayAdapter<String>(this, R.layout.list_career,MainActivity.careers);
        autoCompleteTxt.setAdapter(adapterCareers);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Dropdown for careers
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String career = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Career "+ career, Toast.LENGTH_LONG).show();
                return;
            }
        });


        mAuth = FirebaseAuth.getInstance();


        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextVerifyPassword = (EditText) findViewById(R.id.editTextVerifyPassword);
        //editTextCareer = (EditText) findViewById(R.id.editTextCareer);
        editTextExtras = (EditText) findViewById(R.id.editTextExtras);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        progressBar = (ProgressBar) findViewById(R.id.progressBarReg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerUser:
               if( registerUser()){
                   //test to go to the main activity successful
                   setContentView(R.layout.activity_main);
                   break;
               }


        }

    }

    private Boolean registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String verify_password = editTextVerifyPassword.getText().toString().trim();
        //dropdown from autoCompleteTxt that will receive the career
        String career = autoCompleteTxt.getText().toString();
        String extras = editTextExtras.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        //validating if it is empty
        if(name.isEmpty()){
            editTextName.setError("Full Name is Required");
            editTextName.requestFocus();
            return false;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Provide a Valid Email");
            editTextEmail.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return false;
        }
        //firebase only accepts password with at least 6 characters
        if(password.length()<6){
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
            return false;
        }
        if(verify_password.isEmpty()){
            editTextVerifyPassword.setError("Please verify your password");
            editTextVerifyPassword.requestFocus();
            return false;
        }
        if(!password.equals(verify_password)){
            editTextVerifyPassword.setError("Password does not match");
            editTextVerifyPassword.requestFocus();
            return false;
        }
        if(career.isEmpty()){
            //editTextCareer.setError("Please provide a Career");
            //editTextCareer.requestFocus();
            Log.i(TAG, "Please select a Career!");
            return false;
        }
        if(phone.isEmpty()){
            editTextPhone.setError("Phone Number is required");
            editTextPhone.requestFocus();
            return false;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            editTextPhone.setError("Please provide a valid Phone Number");
            editTextPhone.requestFocus();
            return false;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(name, email, phone, extras, career, password, password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {



                            @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterUser.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                Log.i(TAG, "User created with email: " + email + ", and name: " + name  + ".");
                            }else{
                                Toast.makeText(RegisterUser.this,"Failed to register, try again",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
        startActivity(new Intent(this,MainActivity.class));
        return true;
    }

}
