package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;


/**
 * The viewModel for the MainView Fragment, the viewModel observes the LiveData objects
 * And updates itself when they are changed
 * The mainView updates the MainViewModel on input from the user
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<Movie> movie;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init(Context ctx) {
        if(movie != null) {
            return;
        }
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        filmsterRepository.loadMovies();
    }

    /**
     * The method that the mainView observes for changes
     * Calls an asynchronous method that loads the movie
     * @return an immutable liveData Movie object
     */
    public LiveData<Movie> getMovie() {
        if (movie == null) {
            movie = new MutableLiveData<>();
            loadMovies();
        }
        return movie;
    }

    private void loadMovies() {
        // Do an asynchronous operation to fetch a movie
        movie = filmsterRepository.getCurrentMovie();
    }

    public void addLikedMovie(Movie movie) {
        filmsterRepository.addLikedMovie(movie);
        nextMovie();
    }
    public void addDislikedMovie(Movie movie) {
        filmsterRepository.addDislikedMovie(movie);
        nextMovie();
    }
    public void nextMovie() {
        filmsterRepository.nextMovie();
    }
}
