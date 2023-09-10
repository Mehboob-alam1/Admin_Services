package com.example.mazdoorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mazdoorapp.DetailsActivity;
import com.example.mazdoorapp.ModelProviderType;
import com.example.mazdoorapp.ProvideApprovalActivity;
import com.example.mazdoorapp.R;
import com.example.mazdoorapp.ReportActivity;
import com.example.mazdoorapp.UserInfoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderTypesAdapter extends RecyclerView.Adapter<ProviderTypesAdapter.MyviewHolder> {
    ArrayList<ModelProviderType> list;
    Context context;
    DatabaseReference databaseReference;
    String uId;
    public ProviderTypesAdapter(ArrayList<ModelProviderType> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProviderTypesAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_providers, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderTypesAdapter.MyviewHolder holder, int position) {

        ModelProviderType data = list.get(position);

        holder.providerImage.setImageResource(data.getImage());
        holder.providerType.setText(data.getType());

        databaseReference = FirebaseDatabase.getInstance().getReference("userInfo");
        databaseReference.child(holder.providerType.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        UserInfoModel data = snap.getValue(UserInfoModel.class);

                        uId= data.getUserId();
                    }

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//   holder.itemView.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View view) {
//
//           Toast.makeText(context, ""+holder.providerType.getText().toString(), Toast.LENGTH_SHORT).show();
//         Intent intent=new Intent(context, ProvideApprovalActivity.class);
//         intent.putExtra("type",holder.providerType.getText().toString());
//         context.startActivity(intent);
//       }
//   });




        holder.providerImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+holder.providerType.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ProvideApprovalActivity.class);
                intent.putExtra("type",holder.providerType.getText().toString());
                context.startActivity(intent);
            }
        });


        holder.reports.setOnClickListener(view -> {


            Intent intent = new Intent(context, ReportActivity.class);
            intent.putExtra("type", holder.providerType.getText().toString());
            intent.putExtra("uID", uId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView providerImage;
        TextView providerType;
        TextView reports;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            providerImage = itemView.findViewById(R.id.imgProvider);
            providerType = itemView.findViewById(R.id.txtProvider);
            reports = itemView.findViewById(R.id.reports);
        }
    }
}
