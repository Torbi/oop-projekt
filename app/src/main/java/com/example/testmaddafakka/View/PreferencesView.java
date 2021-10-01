package com.example.testmaddafakka.View;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PreferencesView extends Fragment implements ViewListener {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preferences_view, container, false);

        Spinner genreSpinner = (Spinner) view.findViewById(R.id.genreSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        genreSpinner.setAdapter(genreAdapter);


        Spinner actorSpinner = (Spinner) view.findViewById(R.id.actorSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> actorAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        actorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        actorSpinner.setAdapter(actorAdapter);


        Spinner directorSpinner = (Spinner) view.findViewById(R.id.directorSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> directorAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        directorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        directorSpinner.setAdapter(directorAdapter);


        return view;
    }


    public void update(List<Movie> list){
    }


}