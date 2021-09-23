package com.example.testmaddafakka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.testmaddafakka.View.WatchlistView;

import org.w3c.dom.Text;

public class StartPage extends Fragment {

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
                fr.replace(R.id.fragmentContainer, new Preferences());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }
}