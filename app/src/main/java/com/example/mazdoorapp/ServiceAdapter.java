package com.example.mazdoorapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mazdoorapp.adapters.ProviderAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.viewHolder> {
    ArrayList<UserInfoModel> list;
    DatabaseReference databaseReference;
    Context context;
    String typeOfProvider;


    public ServiceAdapter(ArrayList<UserInfoModel> list, Context context, String typeOfProvider) {
        this.list = list;
        this.context = context;
        this.typeOfProvider = typeOfProvider;
    }

    @NonNull
    @Override

    public ServiceAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.registerd_providers, parent, false);
        return new ServiceAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.viewHolder holder, int position) {
        UserInfoModel data = list.get(position);
        databaseReference = FirebaseDatabase.getInstance().getReference("userInfo");
        holder.name.setText(data.getName());
        holder.providerType.setText(data.getServiceType());
        holder.phoneNumber.setText(data.getPhonenumber());

        Picasso.get().load(data.getImage()).placeholder(R.drawable.user).into(holder.profileImage);


        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context,AdditionToProviderActivity.class);
            i.putExtra("uid",data.getUserId());
            i.putExtra("service",data.getServiceType());
            context.startActivity(i);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name, providerType, phoneNumber;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch aSwitch;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImageR);
            name = itemView.findViewById(R.id.txtUserNameR);
            providerType = itemView.findViewById(R.id.txtProviderTypeR);
            phoneNumber = itemView.findViewById(R.id.txtcontactNumberR);


        }
    }
}
