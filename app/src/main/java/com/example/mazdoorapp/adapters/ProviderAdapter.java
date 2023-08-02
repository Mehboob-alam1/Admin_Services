package com.example.mazdoorapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.example.mazdoorapp.R;
import com.example.mazdoorapp.UserInfoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.viewHolder>{
    ArrayList<UserInfoModel> list;
    DatabaseReference databaseReference;
    Context context;
    String typeOfProvider;


    public ProviderAdapter(ArrayList<UserInfoModel> list, Context context,String typeOfProvider) {
        this.list = list;
        this.context = context;
        this.typeOfProvider=typeOfProvider;
    }

    @NonNull
    @Override

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_user,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
         UserInfoModel data=  list.get(position);
        databaseReference = FirebaseDatabase.getInstance().getReference("userInfo");
         holder.name.setText(data.getName());
         holder.providerType.setText(data.getServiceType());
         holder.phoneNumber.setText(data.getPhonenumber());
        holder.aSwitch.setChecked(data.isVerified());
        Picasso.get().load(data.getImage()).placeholder(R.drawable.user).into(holder.profileImage);

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()) {
                    databaseReference.child("VerPro").child(data.getServiceType()).child(data.getUserId()).setValue(data);
                    databaseReference.child("VerPro").child(data.getServiceType()).child(data.getUserId()).child("verified").setValue(true);

                    databaseReference.child(typeOfProvider).child(data.getUserId()).child("verified").setValue(true);
                    Toast.makeText(context, data.getName() + "\nService is ON", Toast.LENGTH_SHORT).show();


                } else {
                    databaseReference.child("VerPro").child(data.getServiceType()).child(data.getUserId()).setValue(null);
                    databaseReference.child(typeOfProvider).child(data.getUserId()).child("verified").setValue(false);

                    Toast.makeText(context, data.getName() + "\nService is OFF", Toast.LENGTH_SHORT).show();
                }





                ///

            }
        });







    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        TextView name,providerType,phoneNumber;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch aSwitch;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage=itemView.findViewById(R.id.profileImage);
            name=itemView.findViewById(R.id.txtUserName);
            providerType=itemView.findViewById(R.id.txtProviderType);
            phoneNumber=itemView.findViewById(R.id.txtcontactNumber);
            aSwitch=itemView.findViewById(R.id.btnSwitch);

        }
    }
}
