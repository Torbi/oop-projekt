package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.IMDbApiAdapter;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.api.IApiListener;

import java.util.List;

public class FilmsterRepository implements IApiListener {

    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<Movie>> movies;
    private ApiListener listener;

    private FilmsterRepository(Context ctx) {
        listener = new ApiListener();
        listener.addListener(this);
        imdbAdapter = new IMDbApiAdapter(ctx, listener);
        movies = new MutableLiveData<>();
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if(instance == null) {
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

    public void setMovies(List<Movie> movies) {
        this.movies.setValue(movies);
    }

    @Override
    public void update(List<Movie> movies) {
        this.movies.setValue(movies);
    }
}
