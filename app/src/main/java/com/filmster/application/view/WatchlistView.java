package com.filmster.application.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testmaddafakka.R;
import com.google.android.material.tabs.TabLayout;


public class WatchlistView extends Fragment {
    private TabLayout tabLayout;
    private WatchlistTabView likedMedias;
    private WatchlistTabView dislikedMedias;
    private WatchlistTabView watchedMedia;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        initTabLayout();
        initViews();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, likedMedias).commit();

        initTabListener();

        return view;
    }

    private void initViews() {
        likedMedias = new LikedTabView();
        dislikedMedias = new DislikedTabView();
        watchedMedia = new WatchedTabView();
    }

    private void initTabListener() {
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
    }

    private void initTabLayout() {
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Liked"));
        tabLayout.addTab(tabLayout.newTab().setText("Missed"));
        tabLayout.addTab(tabLayout.newTab().setText("Seen"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private Fragment getItem(int position) {
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