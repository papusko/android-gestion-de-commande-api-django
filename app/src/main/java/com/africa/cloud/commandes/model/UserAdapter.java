package com.africa.cloud.commandes.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Callback;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements View.OnClickListener {

    private List<User> userList;
    User user;

    public UserAdapter(List<User> userList, Callback<List<User>> callback) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView prenom, nom, usrname, mail, password, confirmPassword;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}