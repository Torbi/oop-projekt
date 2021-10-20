package com.filmster.application.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.filmster.application.R;

public class DislikedTabView extends WatchlistTabView {

    public DislikedTabView(){
        super(R.layout.fragment_missed_movies);
    }

    @Override
    public void observe() {
        viewModel.getDislikedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });
    }
}