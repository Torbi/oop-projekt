package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testmaddafakka.R;
import com.example.testmaddafakka.model.Actor;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.viewmodel.PreferencesViewModel;

import java.util.ArrayList;
import java.util.List;


public class SearchResults extends Fragment {


    public SearchResults() {
        // Required empty public constructor
    }

    private View view;
    private PreferencesViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_results, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.init(requireContext());


        return view;
    }
}