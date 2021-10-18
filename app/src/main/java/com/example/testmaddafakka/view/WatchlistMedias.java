package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmaddafakka.R;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;

/**
 * Handler of responsibility class a super to Disliked, liked and watched media to
 * remove duplicate code.
 */
public class WatchlistMedias extends Fragment {

    public WatchlistViewModel viewModel;
    public RecyclerView recyclerView;

    private final int fragment;

    public WatchlistMedias(int fragment){
        this.fragment = fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fragment, container, false);

        recyclerView = view.findViewById(R.id.rvMedia);

        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        observe();

        return view;
    }
    public void observe(){
        viewModel.getLikedMedias().observe(getViewLifecycleOwner(), medias -> {
            MediaAdapter adapter = new MediaAdapter(medias);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        });

    }
}
