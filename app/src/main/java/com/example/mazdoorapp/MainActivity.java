package com.example.mazdoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mazdoorapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardView.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProvidersActivity.class)));
        binding.cardViewProfile.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));

        binding.cardViewBanners.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,BannersActivity.class)));

        binding.cardViewUsers.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,AllUserActivity.class)));


        binding.cardUsers.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,UsersActivity.class)));


    }
}