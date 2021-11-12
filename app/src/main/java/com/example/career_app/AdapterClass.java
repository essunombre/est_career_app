package com.example.career_app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    //Arraylist filled with what comes from the Career class.
    ArrayList<Career> list;

    public AdapterClass(ArrayList<Career> list){
        Log.e("list",list.toString() );
      this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.email.setText(list.get(i).getEmail());
        myViewHolder.phone.setText(list.get(i).getPhone());
        myViewHolder.extras.setText(list.get(i).getExtras());
        myViewHolder.career.setText(list.get(i).getCareer());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, phone, extras, career;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            extras = itemView.findViewById(R.id.extras);
            career = itemView.findViewById(R.id.career);
        }
    }
}
