package com.filmster.application.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.filmster.application.R;

/**
 * DislikedTabView a part of the watchlist responsible to show all disliked movies
 */
public class DislikedTabView extends WatchlistTabView {

    public DislikedTabView(){
        super(R.layout.fragment_missed_movies);
    }

    /**
     * Observes the dislikedMedias when it updates,
     * a new adapter is created with the disliked media and adds it to the recyclerView
     */
    @Override
    public void observe() {
        viewModel.getDislikedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });
    }
}