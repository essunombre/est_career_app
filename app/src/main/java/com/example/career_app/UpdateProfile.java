package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.career_app.databinding.ActivityUpdateProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfile extends AppCompatActivity {

    //    private DatabaseReference mDatabase;
    ActivityUpdateProfileBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ButterKnife.bind(this);

        binding.changeName.setText(MainActivity.userdata.name);
        binding.changeEmail.setText(MainActivity.userdata.email);
        binding.changePassword.setText(MainActivity.userdata.password);
        binding.confirmPassword.setText(MainActivity.userdata.confirmPassword);
        binding.changeCareer.setText(MainActivity.userdata.career);
        binding.changeExtras.setText(MainActivity.userdata.extras);
        binding.changePhone.setText(MainActivity.userdata.phone);

        binding.updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String changeName = binding.changeName.getText().toString().trim();
                String changeEmail = binding.changeEmail.getText().toString().trim();
                String changePassword = binding.changePassword.getText().toString().trim();
                String confirmPassword = binding.confirmPassword.getText().toString().trim();
                String changeCareer = binding.changeCareer.getText().toString().trim();
                String changeExtras = binding.changeExtras.getText().toString().trim();
                String changePhone = binding.changePhone.getText().toString().trim();

                updateData(changeName, changeEmail, changePassword, confirmPassword, changeCareer, changeExtras, changePhone);
            }
        });
    }

    private void updateData(String changeName, String changeEmail, String changePassword, String confirmPassword, String changeCareer, String changeExtras, String changePhone) {
        HashMap User = new HashMap();
        User.put("name", changeName);
        User.put("email", changeEmail);
        User.put("password", changePassword);
        User.put("confirmPassword", confirmPassword);
        User.put("career", changeCareer);
        User.put("extras", changeExtras);
        User.put("phone", changePhone);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(MainActivity.idUsuario).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
//                    binding.changeName.setText("");
//                    binding.changeEmail.setText("");
//                    binding.changePassword.setText("");
//                    binding.confirmPassword.setText("");
//                    binding.changeCareer.setText("");
//                    binding.changeExtras.setText("");
//                    binding.changePhone.setText("");
                    Toast.makeText(UpdateProfile.this, "Profile Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateProfile.this, "Profile Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton)
    void accountButton(View view) {
        Toast.makeText(this, "You are in the Account Page Already!", Toast.LENGTH_LONG).show();
    }

    //Search Careers Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.careersSearch)
    void careersSearch(View view) {
        Toast.makeText(this, "Welcome to the Careers Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CareersActivity.class);
        startActivity(intent);
    }

    //Home Buttons
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.homeButton)
    void homeButton(View view) {
        Toast.makeText(this, "Welcome to the Home Page!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}