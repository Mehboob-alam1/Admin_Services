package com.example.mazdoorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private Context context;
    private ArrayList<User> list;
    private String whichActivity;

    public UserAdapter(Context context, ArrayList<User> list, String whichActivity) {
        this.context = context;
        this.list = list;
        this.whichActivity = whichActivity;
    }

    public void filterList(ArrayList<User> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User user = list.get(position);

        try {
            Glide.with(context)
                    .load(user.getUser_image())
                    .into(holder.imgUser);
        } catch (IllegalArgumentException e) {

        }

//        if (user.getDateAuth() !=null) {
//            long difference = Long.parseLong(user.getDateAuth()) - System.currentTimeMillis();
//
//            long hoursPassed = TimeUnit.MILLISECONDS.toHours(difference);
//
//            if (hoursPassed <= 24) {
//                holder.layout.setVisibility(View.VISIBLE);
//            } else {
//                holder.layout.setVisibility(View.GONE);
//            }
//        }
        holder.txtName.setText(user.getFirst_name() + " " + user.getSur_name());
        holder.txtContact.setText(user.getPhone_number());
        if (whichActivity.equals("All")) {
            Gson gson = new Gson();
            String userData = gson.toJson(user);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtContact;
        private ImageView imgUser;
        private LinearLayout layout;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutNew);
            txtName = itemView.findViewById(R.id.txtUser);
            txtContact = itemView.findViewById(R.id.txtContactNumber);
            imgUser = itemView.findViewById(R.id.imgUser);
        }
    }
}
