package com.example.mazdoorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mazdoorapp.databinding.ActivityUsersBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {
private ActivityUsersBinding binding;
private DatabaseReference userRef;
private ArrayList<User> listUser;
private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
userRef= FirebaseDatabase.getInstance().getReference("Users");

listUser= new ArrayList<>();
        binding=ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


fetchUsers();
    }


    private void fetchUsers() {


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listUser.clear();
                    binding.txtNoData.setVisibility(View.GONE);
                    binding.recyclerUser.setVisibility(View.VISIBLE);

                    for (DataSnapshot snap : snapshot.getChildren()) {


                        User user = snap.getValue(User.class);


                        listUser.add(new User(user.getEmail(), user.getPassword(), user.getFirst_name(),
                                user.getSur_name(), user.getPhone_number(),
                                user.getReferral_id(), user.getAddress(),
                                user.getUser_id(), user.getUser_image(), user.getCnic_front(),
                                user.getCnic_back(), user.isBlock(),user.isComplete(),""));

                    }
                    adapter = new UserAdapter(UsersActivity.this,listUser,"All");
                    binding.recyclerUser.setLayoutManager(new LinearLayoutManager(UsersActivity.this));
                    binding.recyclerUser.setAdapter(adapter);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UsersActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}