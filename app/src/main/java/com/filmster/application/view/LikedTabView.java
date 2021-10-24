package com.filmster.application.view;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.filmster.application.R;

/**
 * LikedTabView a part of the watchlist responsible to show all liked movies
 * @author Magnus
 */
public class LikedTabView extends WatchlistTabView {

    public LikedTabView(){
        super(R.layout.fragment_liked_movies);
    }

    /**
     * Observes the likedMedias when it updates,
     * a new adapter is created with the liked media and added to the recyclerView
     */
    @Override
    public void observe(){
        viewModel.getLikedMedias().observe(getViewLifecycleOwner(), medias -> {
            for(int i = 0; i < medias.size(); i++) {
            }
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });

    }

}