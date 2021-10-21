package com.filmster.application.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.filmster.application.R;

/**
 * WatchedTabView a part of the watchlist responsible to show all watched movies
 */

public class WatchedTabView extends WatchlistTabView {

    public WatchedTabView() {
        super(R.layout.fragment_seen_movies);
    }
    /**
     * Observes the watchedMedias when it updates,
     * a new adapter is created with the watched media and adds it to the recyclerView
     */
    @Override
    public void observe(){
        viewModel.getWatchedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });
    }
}