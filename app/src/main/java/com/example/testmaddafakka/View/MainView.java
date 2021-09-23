package com.example.testmaddafakka.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//import com.bumptech.glide.Glide;
import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainView extends Fragment implements ViewListener {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);
        Button watchlistBtn = (Button) view.findViewById(R.id.watchlist);
        Button preferencesBtn = (Button) view.findViewById(R.id.preferences);

        //this is only for cool purposes
        ImageView movie = view.findViewById(R.id.movieImage);

        String path = "https://imdb-api.com/Posters/w780/n9dwu1p5G4qJ4DI5eHJMUbAdOfA.jpg";

        Picasso.get().load(path).into(movie);

        watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new WatchlistView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        preferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, new PreferencesView());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }


    @Override
    public void update(List<Movie> list) {

    }
}
