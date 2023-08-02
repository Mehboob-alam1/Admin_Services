package com.example.mazdoorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.mazdoorapp.databinding.ActivityAllUserBinding;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {
private ActivityAllUserBinding binding;
    ArrayList<ModelProviderType> list;
    ProviderTypesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list=new ArrayList<>();
        list.clear();
        list.add(new ModelProviderType("Carpenter",R.drawable.carpenter));
        list.add(new ModelProviderType("Electrician",R.drawable.electrician));
        list.add(new ModelProviderType("Handiman",R.drawable.handiman));
        list.add(new ModelProviderType("Painter",R.drawable.painter));
        list.add(new ModelProviderType("Sweeper",R.drawable.sweeper));
        list.add(new ModelProviderType("Vehicle Mechanic",R.drawable.vehicle_mechanic));
        list.add(new ModelProviderType("Launderer",R.drawable.laundry));
        list.add(new ModelProviderType("Ac Technician",R.drawable.technician));
        list.add(new ModelProviderType("Mason",R.drawable.mason));
        list.add(new ModelProviderType("Plumber",R.drawable.plumber));
        list.add(new ModelProviderType("Interior Designer",R.drawable.interior_designer));
        adapter=new ProviderTypesAdapter(list,this);
        binding.recyclerContent.setAdapter(adapter);
        binding.recyclerContent.setLayoutManager(new GridLayoutManager(this, 3));

    }
}