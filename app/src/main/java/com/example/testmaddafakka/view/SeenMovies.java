package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;

import java.util.List;

public class SeenMovies extends Fragment{
    private WatchlistViewModel viewModel;
    private NetworkImageView smallMovieImage;
    private TextView movieTitle;
    private TextView movieYear;
    private TextView movieRating;

    public SeenMovies(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seen_movies, container, false);
        smallMovieImage = view.findViewById(R.id.smallMovieTitle);
        movieTitle = view.findViewById(R.id.movieTitleSmall);
        movieYear = view.findViewById(R.id.movieYearSmall);
        movieRating = view.findViewById(R.id.movieRatingSmall);


        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        viewModel.getWatchedMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                for(Movie movie : movies){
                    //new compentn
                    updateMovieDisplayed(movie);
                }
            }
        });


        return view;
    }
    private void updateMovieDisplayed(Movie movie) {
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = movie.getImage();
        url = url.substring(1,url.length()-1);

        if(url.length() > 0)
            smallMovieImage.setImageUrl(url, imageLoader);

        String title = shorten(movie.getTitle());
        movieTitle.setText(checkMovieLength(title));

        String grade = shorten(movie.getRating()) + "/10";
        movieRating.setText(grade);
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