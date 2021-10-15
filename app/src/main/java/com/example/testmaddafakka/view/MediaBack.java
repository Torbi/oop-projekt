package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmaddafakka.R;
import com.example.testmaddafakka.model.IMedia;

public class MediaBack extends Fragment {
    private TextView mediaTitle;
    private TextView mediaYear;
    private TextView mediaRating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_back, container, false);
        mediaTitle = view.findViewById(R.id.mediaTitleBack);
        mediaYear = view.findViewById(R.id.mediaYearBack);
        mediaRating = view.findViewById(R.id.mediaRatingBack);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String[] data = bundle.getString("data").split("@");
            update(data[0], data[1], data[2]);
        }

        return view;
    }

    private void update(String title, String rating, String year) {
        mediaTitle.setText(title);
        String ratingTemp = rating + "/10"; //Bad to concat in setText
        mediaRating.setText(ratingTemp);
        mediaYear.setText(year);
    }
}