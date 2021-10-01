package com.example.testmaddafakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.testmaddafakka.Controller.ModelAcces;
import com.example.testmaddafakka.View.MainView;

/**
 * Skriver fulkod här
 */
public class MainActivity extends AppCompatActivity {

    private ModelAcces modelAcces;
    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("re");
        mainView = new MainView();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, mainView);
        fragmentTransaction.commit();

        //this.mainView.view.findViewById(R.id.watchlist);
        this.modelAcces = new ModelAcces(mainView, mainView.getFragmentManager(), getApplicationContext());
    }
}