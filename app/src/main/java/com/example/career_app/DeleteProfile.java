package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteProfile extends AppCompatActivity {

    TextView userEmail;
    Button deleteAccount;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);
        ButterKnife.bind(this);

        userEmail = findViewById(R.id.emailDelete);
        deleteAccount = findViewById(R.id.btnDeleteProfile);
        //delete button
        deleteAccount = findViewById(R.id.btnDeleteProfile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userEmail.setText(firebaseUser.getEmail());

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DeleteProfile.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your account "+
                        "from the system and you won't be able to access the app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DeleteProfile.this, "Account Deleted",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DeleteProfile.this, MainActivity.class);
                                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(DeleteProfile.this, task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });



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