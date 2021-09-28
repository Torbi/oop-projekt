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

    private View view;

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
        view = inflater.inflate(R.layout.fragment_start_page, container, false);
        Button watchlistBtn = (Button) view.findViewById(R.id.watchlist);
        Button preferencesBtn = (Button) view.findViewById(R.id.preferences);

        //this is only for cool purposes
        /*

        IAdapter adapter = IMDbApiAdapter.getIMDBAdapter();
        List<Movie> movieList = adapter.get250Movies();
        Movie movie = movieList.get(0);
        String newPath = movie.getImage();
        System.out.println(newPath + " pathen");

         */
        ImageView movieImage = view.findViewById(R.id.movieImage);
        Picasso.get().load("https://imdb-api.com/images/original/MV5BNTE4YWE4NmEtYWY0ZS00ZDU4LTkxY2EtNTk2MDY1MDk5MTgyXkEyXkFqcGdeQXVyOTkwMTQ5MTI@._V1_Ratio0.6800_AL_.jpg").into(movieImage);

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
        NetworkImageView niv = (NetworkImageView) view.findViewById(R.id.movieImage);
        if(url.length() > 0)
            niv.setImageUrl(url, imageLoader);
        //niv.setDefaultImageResId(R.drawable._default);
        //niv.setErrorImageResId(R.drawable.error);
    }
}
