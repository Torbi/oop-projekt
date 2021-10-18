package com.example.testmaddafakka.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testmaddafakka.R;

/**
 * Liked Medias a part of the watchlist responsable to show all liked movies
 */
public class LikedMedias extends WatchlistMedias {

    public LikedMedias(){
        super(R.layout.fragment_liked_movies);
    }

    @Override
    public void observe(){
        viewModel.getLikedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });

    }

}