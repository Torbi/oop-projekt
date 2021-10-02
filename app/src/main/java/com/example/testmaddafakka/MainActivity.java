package com.example.testmaddafakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.testmaddafakka.controller.ModelAcces;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.view.MainView;
import com.example.testmaddafakka.repository.FilmsterRepository;
import com.example.testmaddafakka.viewmodel.MainViewModel;
import com.example.testmaddafakka.databinding.ActivityMainBinding;

import java.util.List;

/**
 * Skriver fulkod här
 */
public class MainActivity extends AppCompatActivity {

    private ModelAcces modelAcces;
    private MainView mainView;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainView = new MainView();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, mainView);
        fragmentTransaction.commit();


    }
}

