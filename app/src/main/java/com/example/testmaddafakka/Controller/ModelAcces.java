package com.example.testmaddafakka.Controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testmaddafakka.MainActivity;
import com.example.testmaddafakka.Model.IAdapter;
import com.example.testmaddafakka.Model.IMDbApiAdapter;
import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.View.Listener;
import com.example.testmaddafakka.View.MainView;
import com.example.testmaddafakka.View.PreferencesView;
import com.example.testmaddafakka.View.WatchlistView;

import java.util.List;
import java.util.Objects;

/**
 * The accesspoint for anything that wants to communicate with the model
 * All communication between controllers and model will go through this
 */
public class ModelAcces {

    private Listener listener;
    private Context context;
    private IAdapter adapter;

    Button watchlistBtn;
    Button preferencesBtn;

    MainView mainView;
    FragmentManager fragmentManager;

    public ModelAcces(MainView mainView, FragmentManager fragmentManager, Context context) {
        this.mainView = mainView;
        this.fragmentManager = fragmentManager;
        this.context = context;
        listener = new Listener();
        listener.addListener(this.mainView);
        adapter = new IMDbApiAdapter(context, listener);
        adapter.get250Movies();
    }

    /*
    public void populate(){
        //Test github
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, this.mainView);
        fragmentTransaction.commit();

    }

     */
    /*
    public void viewShit(){
        watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = mainView.getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new WatchlistView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        preferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = mainView.getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new PreferencesView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

    }

     */
    /**
     * Updates all current listeners
     * @param movies a list of movies
     */
    public void updateView(List<Movie> movies) {
        listener.notifyListeners(movies);
    }
}
