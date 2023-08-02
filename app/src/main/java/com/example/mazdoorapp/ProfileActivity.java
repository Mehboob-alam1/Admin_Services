package com.example.mazdoorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mazdoorapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shp = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        CheckLogin();

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

    }

    public void CheckLogin() {
        if (shp == null)
            shp = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);


        String userName = shp.getString("name", "");

        if (userName != null && !userName.equals("")) {
            binding.txtInfo.setText("Welcome  " + userName);

        } else {
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }


    public void Logout() {
        try {
            if (shp == null)
                shp =getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

            shpEditor = shp.edit();
            shpEditor.putString("name", "");
            shpEditor.commit();

            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
          startActivity(i);
           finish();

        } catch (Exception ex) {
            Toast.makeText(ProfileActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
