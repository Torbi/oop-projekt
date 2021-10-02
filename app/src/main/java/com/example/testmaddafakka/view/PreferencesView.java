package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.R;

import java.util.List;


public class PreferencesView extends Fragment {

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
}