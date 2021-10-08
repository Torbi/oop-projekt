package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;

import java.util.List;

/**
 * Liked Medias a part of the watchlist responsable to show all liked movies
 */
public class LikedMedias extends Fragment {

    private View view;
    private WatchlistViewModel viewModel;

    public LikedMedias() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liked_movies, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvMedia);


        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        viewModel.getLikedMedias().observe(getViewLifecycleOwner(), new Observer<List<IMedia>>() {
            @Override
            public void onChanged(List<IMedia> medias) {
                MediaAdapter adapter = new MediaAdapter(medias);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });

        return view;
    }
}