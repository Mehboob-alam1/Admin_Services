package com.example.mazdoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mazdoorapp.databinding.ActivityAdditionToProviderBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdditionToProviderActivity extends AppCompatActivity {
private ActivityAdditionToProviderBinding binding;
private String userId,service;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdditionToProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     userId=   getIntent().getStringExtra("uid");
     service=   getIntent().getStringExtra("service");

     databaseReference= FirebaseDatabase.getInstance().getReference("userInfo");

     binding.btnAdd.setOnClickListener(view -> {

         if (binding.etAddProfit.getText().toString().isEmpty()){
             Toast.makeText(this, "Add a message", Toast.LENGTH_SHORT).show();
         }else{

         databaseReference.child(service).child(userId)
                 .child("MessageAdmin")
                 .setValue(binding.etAddProfit.getText().toString())
                 .addOnCompleteListener(task -> {
                     if (task.isComplete() && task.isSuccessful()){
                         Toast.makeText(this, "Message sent to provider", Toast.LENGTH_SHORT).show();
                         binding.etAddProfit.setText("");
                     }
                 }).addOnFailureListener(e -> {
                     Toast.makeText(this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                 });


         }



     });


    }
}