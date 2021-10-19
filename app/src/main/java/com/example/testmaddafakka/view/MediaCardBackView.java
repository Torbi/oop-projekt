package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmaddafakka.R;
import com.example.testmaddafakka.model.IMedia;

public class MediaCardBackView extends Fragment {
    private TextView aboutMedia;
    private TextView aboutTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_back, container, false);
        aboutMedia = view.findViewById(R.id.about);
        aboutTitle = view.findViewById(R.id.aboutTitle);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String about = bundle.getString("about");
            update(about);
        }

        return view;
    }

    private void update(String about) {
        String aboutTilte = "About";
        aboutTitle.setText(aboutTilte);
        aboutMedia.setText(about);

    }
}