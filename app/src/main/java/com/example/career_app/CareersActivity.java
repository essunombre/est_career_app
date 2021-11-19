package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CareersActivity extends AppCompatActivity {
    DatabaseReference mDataBase;
    ArrayList<Career> list;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careers);
        ButterKnife.bind(this);

        mDataBase = FirebaseDatabase.getInstance().getReference("Users");
//        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.rv);
        //this makes recyclerView to work :)
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchView = findViewById(R.id.searchView);

        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    ArrayList<Career> myList = new ArrayList<>();
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    for (DataSnapshot t: task.getResult().getChildren()) {
                        Log.e("this", t.getValue().toString());
                        String name = (String) t.child("name").getValue();
                        String email = (String) t.child("email").getValue();
                        String phone = (String) t.child("phone").getValue();
                        String extras = (String) t.child("extras").getValue();
                        String career = (String) t.child("career").getValue();
                        Career c = new Career(email, name, phone, extras, career);
                        myList.add(c);
                    }
                    AdapterClass adapterClass = new AdapterClass(myList);
                    recyclerView.setAdapter(adapterClass);
                }
                if(searchView != null){
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            search(s);
                            return true;
                        }
                    });
                }
            }
        });


    }

//    protected void whatever(){
//        super.onStart();
//        if(mDataBase != null){
//            mDataBase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.exists()){
//                        Toast.makeText(CareersActivity.this, "There is data here!", Toast.LENGTH_LONG).show();
//                        list = new ArrayList<>();
//                        for(DataSnapshot ds : dataSnapshot.getChildren()){
//                            list.add(ds.getValue(Career.class));
//                        }
//                        AdapterClass adapterClass = new AdapterClass(list);
//                        recyclerView.setAdapter(adapterClass);
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(CareersActivity.this, "Database error", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//        if(searchView != null){
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//
//
//                @Override
//                public boolean onQueryTextSubmit(String s) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String s) {
//                    search(s);
//                    return true;
//                }
//            });
//        }
//    }

    private void search(String str){
        ArrayList<Career> myList = new ArrayList<>();
        for(Career object: list){
            if(object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object) ;
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }

    @OnClick(R.id.searchView) void search(View view ){

    }
    //Account Button
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.accountButton) void clickAccount(View view){
        Toast.makeText(this, "Welcome to the accounts!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, manageProfile.class);
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