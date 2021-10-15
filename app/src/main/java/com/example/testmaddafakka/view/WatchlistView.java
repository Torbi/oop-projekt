package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;
import com.google.android.material.tabs.TabLayout;


public class WatchlistView extends Fragment {
    private TabLayout tabLayout;
    private LikedMedias likedMedias;
    private DislikedMedias dislikedMedias;
    private WatchedMedia watchedMedia;
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

        likedMedias = new LikedMedias();
        dislikedMedias = new DislikedMedias();
        watchedMedia = new WatchedMedia();

        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());

        viewModel.getMedia().observe(getViewLifecycleOwner(), media -> System.out.println("yeet"));

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, likedMedias).commit();
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
                return likedMedias;
            case 1:
                return dislikedMedias;
            case 2:
                return watchedMedia;
            default:
                return null;
        }
    }
}