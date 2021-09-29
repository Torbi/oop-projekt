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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//import com.bumptech.glide.Glide;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.Model.IAdapter;
import com.example.testmaddafakka.Model.IMDbApiAdapter;
import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.SingletonRequestQueue;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainView extends Fragment implements ViewListener {

    public View view;
    public Button test;
    private ImageView movieImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start_page, container, false);
        Button watchlistBtn = (Button) view.findViewById(R.id.watchlist);
        Button preferencesBtn = (Button) view.findViewById(R.id.preferences);

        this.movieImage = view.findViewById(R.id.movieImage);

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
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = list.get(0).getImage();
        url = url.substring(1,url.length()-1);

        NetworkImageView niv = (NetworkImageView) view.findViewById(R.id.movieImage);
        if(url.length() > 0)
            niv.setImageUrl(url, imageLoader);
    }

}
