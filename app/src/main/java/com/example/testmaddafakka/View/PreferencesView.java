package com.example.testmaddafakka.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;

import java.util.List;


public class PreferencesView extends Fragment implements ViewListener {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preferences_view, container, false);

        Button addGenresBtn = (Button) view.findViewById(R.id.addGenresBtn);
        Button addActorsBtn = (Button) view.findViewById(R.id.addActorsBtn);
        Button addDirectorsBtn = (Button) view.findViewById(R.id.addDirectorsBtn);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fragmentContainerView);
        frameLayout.setVisibility(View.INVISIBLE);

        addGenresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new Genres());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        addActorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new Actors());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        addDirectorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainerView, new Directors());
                fr.addToBackStack(null);
                fr.commit();
                frameLayout.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    public void update(List<Movie> list){
    }


}