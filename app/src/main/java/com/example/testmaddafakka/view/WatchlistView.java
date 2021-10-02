package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.R;
import com.google.android.material.tabs.TabLayout;


import java.util.List;


public class WatchlistView extends Fragment implements ViewListener {
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Liked"));
        tabLayout.addTab(tabLayout.newTab().setText("Missed"));
        tabLayout.addTab(tabLayout.newTab().setText("Seen"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, new LikedMovies()).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, getItem(tab.getPosition())).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LikedMovies likedMovies = new LikedMovies();
                return likedMovies;
            case 1:
                MissedMovies missedMovies = new MissedMovies();
                return missedMovies;
            case 2:
                SeenMovies seenMovies = new SeenMovies();
                return seenMovies;
            default:
                return null;
        }
    }


    public void update(List<Movie> list){
    }
}