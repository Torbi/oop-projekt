package com.example.testmaddafakka.repository;

import android.content.Context;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.IMDbApiAdapter;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 */
public class FilmsterRepository implements IApiListener {

    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<Movie> currentMovie;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 0;


    private FilmsterRepository(Context ctx) {

        movies = new MutableLiveData<>();
        currentMovie = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);

        imdbAdapter = new IMDbApiAdapter(ctx);
        loadMovies();
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }

    public void loadMovies() {
        imdbAdapter.get250Movies();
    }

    public MutableLiveData<List<Movie>> getTop250Movies() {
        imdbAdapter.get250Movies();
        return movies;
    }

    public MutableLiveData<Movie> getCurrentMovie() {
        this.currentMovie.setValue(filmster.getCurrentMovie());
        return this.currentMovie;
    }

    public MutableLiveData<List<Movie>> getCurrentMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies.setValue(movies);
    }

    @Override
    public void update(Movie movie) {
        this.currentMovie.setValue(movie);
        //this.movies.setValue(movies);
    }

    public void addLikedMovie(Movie movie) {
        filmster.addLikedMovie(movie);
        nextMovie();
    }

    public void addDislikedMovie(Movie movie) {
        filmster.addDislikedMovie(movie);
        nextMovie();
    }

    public void nextMovie() {
        listener.notifyListeners(this.movies.getValue().get(current));
        current++;
    }
}
