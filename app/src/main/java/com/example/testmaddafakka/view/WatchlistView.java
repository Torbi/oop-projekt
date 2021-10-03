package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;
import com.google.android.material.tabs.TabLayout;


import java.util.List;


public class WatchlistView extends Fragment {
    TabLayout tabLayout;
    private LikedMovies likedMovies;
    private MissedMovies missedMovies;
    private SeenMovies seenMovies;
    private WatchlistViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Liked"));
        tabLayout.addTab(tabLayout.newTab().setText("Missed"));
        tabLayout.addTab(tabLayout.newTab().setText("Seen"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        likedMovies = new LikedMovies();
        missedMovies = new MissedMovies();
        seenMovies = new SeenMovies();

        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());

        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                //Här  ska de va nått
                System.out.println("KUKEN");
            }
        });

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, likedMovies).commit();
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
                return likedMovies;
            case 1:
                return missedMovies;
            case 2:
                return seenMovies;
            default:
                return null;
        }
    }


    private String shorten(String text) {
        String temp = text.substring(1, text.length() - 1);
        return temp;
    }

    private String checkMovieLength(String title) {
        if (title.length() > 13) {
            return title.substring(0, 14) + "...";
        }
        return title;
    }
}