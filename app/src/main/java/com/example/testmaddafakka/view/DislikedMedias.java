package com.example.testmaddafakka.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.testmaddafakka.R;

public class DislikedMedias extends WatchlistMedias {

    public DislikedMedias(){
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