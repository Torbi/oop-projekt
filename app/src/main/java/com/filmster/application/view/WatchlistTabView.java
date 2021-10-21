package com.filmster.application.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.filmster.application.R;
import com.filmster.application.viewmodel.WatchlistViewModel;

/**
 * Abstract super class to Disliked, liked and watched media to remove duplicate code.
 */
public abstract class WatchlistTabView extends Fragment {

    public WatchlistViewModel viewModel;
    public RecyclerView recyclerView;

    private final int fragment;

    public WatchlistTabView(int fragment){
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

    public abstract void observe();
}
