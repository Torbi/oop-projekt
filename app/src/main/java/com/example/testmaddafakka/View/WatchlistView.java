package com.example.testmaddafakka.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;

import java.util.List;


public class WatchlistView extends Fragment implements ViewListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);
        return view;
    }


    public void update(List<Movie> list){
    }
}