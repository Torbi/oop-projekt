package com.filmster.application.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmaddafakka.R;

/**
 * The backside of the card that displays the mediaobject
 * Gets its arguments from bundle because the fragment keeps getting destroyed
 */
public class MediaCardBackView extends Fragment {
    private TextView aboutText;
    private TextView aboutTitle;
    private TextView aboutYear;
    private TextView aboutRating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_back, container, false);
        initTextViews(view);

        getBundleArgs();

        return view;
    }

    private void initTextViews(View view) {
        aboutText = view.findViewById(R.id.aboutText);
        aboutTitle = view.findViewById(R.id.aboutTitle);
        aboutYear = view.findViewById(R.id.aboutYear);
        aboutRating = view.findViewById(R.id.aboutRating);
    }

    private void getBundleArgs() {
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String about = bundle.getString("about");
            String year = bundle.getString("year");
            String name = bundle.getString("name");
            String rating = bundle.getString("rating");
            update(about, year, name, rating);
        }
    }

    @SuppressLint("SetTextI18n")
    private void update(String about, String year, String name, String rating) {
        aboutTitle.setText(name);
        aboutText.setText(about);
        aboutYear.setText(year);
        aboutRating.setText(rating + "/10");
    }
}