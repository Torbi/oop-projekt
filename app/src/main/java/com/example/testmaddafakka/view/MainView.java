package com.example.testmaddafakka.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

//import com.bumptech.glide.Glide;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.viewmodel.MainViewModel;

import java.util.List;

public class MainView extends Fragment {

    public View view;
    public Button test;
    private ImageView movieImage;
    private MainViewModel viewModel;
    private Movie currentMovie;
    private WatchlistView watchlistView;
    private PreferencesView preferencesView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start_page, container, false);


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(requireContext());
        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                currentMovie = movie;
                updateMovieDisplayed(currentMovie);
            }
        });

        watchlistView = new WatchlistView();
        preferencesView = new PreferencesView();

        Button watchlistBtn = (Button) view.findViewById(R.id.watchlist);
        Button preferencesBtn = (Button) view.findViewById(R.id.preferences);
        ImageView likeBtn = view.findViewById(R.id.like);
        ImageView dislikeBtn = view.findViewById(R.id.dislike);
        ImageView seenBtn = view.findViewById(R.id.seen);

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Like");
                viewModel.addLikedMovie(currentMovie);
                System.out.println("MAIN VIEW " + currentMovie);
                viewModel.nextMovie();
            }
        });

        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Dislike");
                viewModel.addDislikedMovie(currentMovie);
                viewModel.nextMovie();

            }
        });
        seenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Seen");
            }
        });
        this.movieImage = view.findViewById(R.id.movieImage);

        watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, watchlistView);
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        preferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer, preferencesView);
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        return view;
    }

    private void updateMovieDisplayed(Movie movie) {
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = movie.getImage();
        url = url.substring(1,url.length()-1);

        NetworkImageView niv = (NetworkImageView) view.findViewById(R.id.movieImage);
        if(url.length() > 0)
            niv.setImageUrl(url, imageLoader);

        TextView movieTitle = view.findViewById(R.id.movieTitle);
        TextView imdbGrade = view.findViewById(R.id.movieRating);
        TextView movieYear = view.findViewById(R.id.movieYear);

        String title = shorten(movie.getTitle());
        movieTitle.setText(checkMovieLength(title));

        String grade = shorten(movie.getRating()) + "/10";
        imdbGrade.setText(grade);
        movieYear.setText(shorten(movie.getYear()));

    }
    private String shorten(String text){
        String temp = text.substring(1, text.length()-1);
        return temp;
    }
    private String checkMovieLength(String title){
        if(title.length() > 15){
            return title.substring(0, 16) + "...";
        }
        return title;
    }

}
