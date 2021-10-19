package com.filmster.application.view;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.filmster.application.R;
import com.filmster.application.viewmodel.PreferencesViewModel;


public class SearchResultsView extends Fragment {


    public SearchResultsView() {
        // Required empty public constructor
    }

    private View view;
    private PreferencesViewModel viewModel;
    private ConstraintLayout constraintLayout;
    private SearchResultAdapter searchResultAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_results, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);


        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.init(requireContext());
        viewModel.getSearchResults().observe(getViewLifecycleOwner(), medias ->{
            searchResultAdapter = new SearchResultAdapter(medias);
            recyclerView.setAdapter(searchResultAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            searchResultAdapter.setOnItemClickListener(new SearchResultAdapter.ClickListener() {
                @Override
                public void onItemClick(int pos, View view) {
                    System.out.println(pos + " position click"); // pos is position of clicked view in recyclerview
                    viewModel.ChosenID(pos);
                }
            });
        });






        return view;
    }


}