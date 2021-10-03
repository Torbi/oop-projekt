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
    private Spinner genreSpinner;
    private Spinner actorSpinner;
    private Spinner directorSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preferences_view, container, false);

        genreSpinner = (Spinner) view.findViewById(R.id.genreSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        setSpinner(genreSpinner, genreAdapter);

        actorSpinner = (Spinner) view.findViewById(R.id.actorSpinner);
        ArrayAdapter<CharSequence> actorAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        setSpinner(actorSpinner, actorAdapter);

        directorSpinner = (Spinner) view.findViewById(R.id.directorSpinner);
        ArrayAdapter<CharSequence> directorAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        setSpinner(directorSpinner, directorAdapter);


        return view;
    }
    public void setSpinner(Spinner spinner, ArrayAdapter<CharSequence> content){
        // Specify the layout to use when the list of choices appears
        content.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(content);
    }

}