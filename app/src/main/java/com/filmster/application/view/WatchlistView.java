package com.filmster.application.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.filmster.application.R;
import com.filmster.application.model.sortingstrategies.ISortMethod;
import com.filmster.application.viewmodel.WatchlistViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * The WatchlistView is a fragment and is the UI for the users watchlist.
 * It has 3 tabs liked, disliked, and watched medias.
 */
public class WatchlistView extends Fragment {
    private TabLayout tabLayout;
    private WatchlistTabView likedMedias;
    private WatchlistTabView dislikedMedias;
    private WatchlistTabView watchedMedia;
    private View view;
    private WatchlistViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        initAndListen2ViewModel();
        initTabLayout();
        initViews();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, likedMedias).commit();

        initTabListener();
        initSpinnerListener();
        return view;
    }

    private void initViews() {
        likedMedias = new LikedTabView();
        dislikedMedias = new DislikedTabView();
        watchedMedia = new WatchedTabView();
    }

    private void initAndListen2ViewModel() {
        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        viewModel.getSortingMethods().observe(getViewLifecycleOwner(), this::populateSpinner);
    }

    private void initSpinnerListener() {
        Spinner spinner = view.findViewById(R.id.watchlistSortSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortMethod = spinner.getSelectedItem().toString();
                viewModel.sortWatchlist(sortMethod);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateSpinner(List<ISortMethod> sortMethods) {
        Spinner spinner = view.findViewById(R.id.watchlistSortSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        for(int i = 0; i < sortMethods.size(); i++){
            spinnerAdapter.add(sortMethods.get(i).getName());
        }

        spinnerAdapter.notifyDataSetChanged();
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