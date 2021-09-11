package com.example.testmaddafakka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testmaddafakka.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.movieTitle.setText("Fragment test");

    }
}