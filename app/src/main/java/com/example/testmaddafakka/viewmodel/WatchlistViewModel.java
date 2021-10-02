package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;

import java.util.List;

public class WatchlistViewModel extends ViewModel {

    private FilmsterRepository filmsterRepository;
    private MutableLiveData<List<Movie>> likedMovies;
    private MutableLiveData<List<Movie>> dislikedMovies;
    private MutableLiveData<List<Movie>> watchedMovies;

    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }

}
