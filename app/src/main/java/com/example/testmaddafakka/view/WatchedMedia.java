package com.example.testmaddafakka.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testmaddafakka.R;

public class WatchedMedia extends WatchlistMedias {

    public WatchedMedia() {
        super(R.layout.fragment_seen_movies);
    }
    @Override
    public void observe(){
        viewModel.getWatchedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });
    }
}