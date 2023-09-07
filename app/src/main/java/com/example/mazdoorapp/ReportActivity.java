package com.example.mazdoorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mazdoorapp.databinding.ActivityReportBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    String uId;
    String userType;
    private ActivityReportBinding binding;
    private DatabaseReference databaseReference;
    ArrayList<UserInfoModel> list;
    List<String> formattedUserList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userType = getIntent().getStringExtra("type");
        uId = getIntent().getStringExtra("uID");


        databaseReference = FirebaseDatabase.getInstance().getReference("userInfo");

        list = new ArrayList<>();
        databaseReference.child(userType).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {


                    list.clear();

                    for (DataSnapshot snap : snapshot.getChildren()) {
                        UserInfoModel data = snap.getValue(UserInfoModel.class);
                        list.add(data);

                    }

                    for (int i = 0; i < list.size(); i++) {
                        UserInfoModel userInfo = list.get(i);
                        String formattedString = (i + 1) + "- " + userInfo.getName() + " (" + userInfo.getServiceType() + ")";
                        formattedUserList.add(formattedString);
                    }

                    // Create an ArrayAdapter to populate the ListView
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportActivity.this, android.R.layout.simple_list_item_1, formattedUserList);

                    // Set the adapter to the ListView

                    binding.listView.setAdapter(adapter);
                } else {

                    Toast.makeText(ReportActivity.this, "No data Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReportActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}