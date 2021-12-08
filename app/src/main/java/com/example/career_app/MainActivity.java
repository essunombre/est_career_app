package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static User userdata;
    public static String idUsuario;
    private TextView register;
    private TextView forgotPassword;


    private static final String TAG = "MainActivity";
    private EditText editTextEmail, editTextPassword;

    private FirebaseAuth myAuth;

    private Button signIn;

    //initializing edittext


    //Whatever Sign in button does, goes inside this method.
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.signIn) void signIn(View view){
        userLogin();
//        Toast.makeText(this, "log in!", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, Home.class);
//        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        //signIn = (Button) findViewById(R.id.signIn);
        //signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        forgotPassword = (TextView) findViewById((R.id.forgotPassword));
        forgotPassword.setOnClickListener(this);

        myAuth = FirebaseAuth.getInstance();

        //



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

            //case R.id.signIn:
                //userLogin();
                //break;

        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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
        if(password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());
                    MainActivity.idUsuario = user.getUid();

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            User user = dataSnapshot.getValue(User.class);
                            MainActivity.userdata = user;
                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });




                    Toast.makeText(MainActivity.this,"Welcome!",Toast.LENGTH_LONG).show();
                    Log.i(TAG, "User with email: " + email + " logged successfully.");

                    startActivity(new Intent(MainActivity.this, Home.class));


                }else{
                    Toast.makeText(MainActivity.this,"Failed to login! Please verify your credentials!",Toast.LENGTH_LONG).show();
                    Log.i(TAG, "User with email: " + email + " failed to login!.");
                }
            }
        });
        }

    }