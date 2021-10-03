package com.example.testmaddafakka.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;

import java.util.List;

public class WatchlistViewModel extends ViewModel {
    private MutableLiveData<Movie> movie;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<List<Movie>> likedMovies;
    private MutableLiveData<List<Movie>> dislikedMovies;
    private MutableLiveData<List<Movie>> watchedMovies;

    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);

    }

    public LiveData<Movie> getMovie() {
        if (movie == null) {
            movie = new MutableLiveData<>();
            loadMovies();
        }
        return movie;
    }

    public LiveData<List<Movie>> getLikedMovies(){
        if(likedMovies == null){
            likedMovies = new MutableLiveData<>();
        }
        likedMovies.setValue(filmsterRepository.getLikedMovies());
        return likedMovies;
    }

    public LiveData<List<Movie>> getDislikedMovies(){
        if(dislikedMovies == null) {
            dislikedMovies = new MutableLiveData<>();
        }
        dislikedMovies.setValue(filmsterRepository.getDislikedMovies());

        return dislikedMovies;
    }
    public LiveData<List<Movie>> getWatchedMovies(){
        if(watchedMovies == null){
            watchedMovies = new MutableLiveData<>();
        }
        watchedMovies.setValue(filmsterRepository.getWatchedMovies());
        return watchedMovies;
    }


    private void loadMovies() {
        // Do an asynchronous operation to fetch a movie
        movie = filmsterRepository.getCurrentMovie();
    }

}
