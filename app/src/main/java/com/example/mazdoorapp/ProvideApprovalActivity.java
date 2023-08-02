package com.example.mazdoorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mazdoorapp.adapters.ProviderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProvideApprovalActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    UserInfoModel model;
    ArrayList<UserInfoModel> list;
    RecyclerView recyclerView;
    ProviderAdapter adapter;
    String typeOfProvider;
    TextView nodata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_approval);
        recyclerView = findViewById(R.id.recyclerProvider);
        nodata=findViewById(R.id.txtNodata);

        recyclerView.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);

      typeOfProvider=  getIntent().getStringExtra("type");
        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("userInfo");
        model = new UserInfoModel();

        databaseReference.child(typeOfProvider).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    nodata.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        UserInfoModel data = snap.getValue(UserInfoModel.class);

                        list.add(data);
                    }
                    adapter = new ProviderAdapter(list, ProvideApprovalActivity.this,typeOfProvider);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProvideApprovalActivity.this));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProvideApprovalActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}