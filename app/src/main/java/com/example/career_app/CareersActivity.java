package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CareersActivity extends AppCompatActivity {
    DatabaseReference mDataBase;
    ArrayList<Career> list;
    ArrayList<Career> backup;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careers);
        ButterKnife.bind(this);

        handleIntent(getIntent());

        mDataBase = FirebaseDatabase.getInstance().getReference("Users");
//        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.rv);
        //this makes recyclerView to work :)
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        searchView = findViewById(R.id.searchView);

        mDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    list = new ArrayList<>();
                    backup = new ArrayList<>();
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    for (DataSnapshot t: task.getResult().getChildren()) {
                        String name = (String) t.child("name").getValue();
                        String email = (String) t.child("email").getValue();
                        String phone = (String) t.child("phone").getValue();
                        String extras = (String) t.child("extras").getValue();
                        String career = (String) t.child("career").getValue();
                        Career c = new Career(career, email, extras, name, phone);
                        list.add(c);
                        backup.add(c);
                    }

                    setAdapter(list);
                }

            }
        });

    }

    private void doMySearch(String query) {
        // firebase call
        query = query.toLowerCase();
        final ArrayList<Career> results = new ArrayList<>();
        for (Career model : this.list) {
            final String text = model.get().toLowerCase();
            if (text.contains(query)) {
                results.add(model);
            }
        }

        if (query.isEmpty()) {
            this.list = this.backup;
            this.setAdapter(this.list);
        } else {
            setAdapter(results);
        }
    }

    void setAdapter(ArrayList<Career> list) {
        AdapterClass adapterClass = new AdapterClass(list);
        recyclerView.setAdapter(adapterClass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.option_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(searchView.getContext(), query, Toast.LENGTH_SHORT).show();
                doMySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doMySearch(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


}