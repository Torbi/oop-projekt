package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private String successMessage = "Login was successful";

    private MutableLiveData<List<Movie>> movies;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init(Context ctx) {
        if(movies != null) {
            return;
        }
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        filmsterRepository.loadMovies();
    }

    public LiveData<List<Movie>> getUsers() {
        if (movies == null) {
            movies = new MutableLiveData<List<Movie>>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {
        // Do an asynchronous operation to fetch users.
        movies = filmsterRepository.getTop250Movies();
    }


}
