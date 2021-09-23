package com.example.testmaddafakka.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;

import java.util.List;

public class MainView extends Fragment implements ViewListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);
        Button watchlistBtn = (Button) view.findViewById(R.id.watchlist);
        Button preferencesBtn = (Button) view.findViewById(R.id.preferences);

        watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new WatchlistView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        preferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new PreferencesView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }

    @Override
    public void update(List<Movie> list) {

    }
}
