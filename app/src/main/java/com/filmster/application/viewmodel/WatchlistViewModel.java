package com.filmster.application.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.sortingstrategies.ISortMethod;
import com.filmster.application.repository.FilmsterRepository;

import java.util.List;

/**
 * ViewModel for the watchlist part of the application, exposes relevant data to the view
 * with the help of livedata objects
 * @author Torbjörn
 */
public class WatchlistViewModel extends ViewModel {
    private MutableLiveData<IMedia> media;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<List<IMedia>> likedMedias;
    private MutableLiveData<List<IMedia>> dislikedMedias;
    private MutableLiveData<List<IMedia>> watchedMedias;
    private MutableLiveData<List<ISortMethod>> sortMethods;

    /**
     * Initialises some data
     * @param ctx - The application context
     */
    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }

    /**
     * Exposes IMedia objects to the view wrapped in LiveData objects
     * @return immutable list of IMedia
     */
    public LiveData<IMedia> getMedia() {
        if (media == null) {
            media = new MutableLiveData<>();
            loadMedias();
        }
        return media;
    }

    private void loadMedias() {
        media = filmsterRepository.getCurrentMedia();
    }

    /**
     * getLikedMedias check if there are any liked medias if not it creates a new list.
     * Otherwise it get likedMedias from filmsterRepository and returns it.
     * @return the users liked medias
     */
    public LiveData<List<IMedia>> getLikedMedias(){
        if(likedMedias == null){
            likedMedias = new MutableLiveData<>();
        }
        likedMedias.setValue(filmsterRepository.getLikedMedias());
        return likedMedias;
    }

    /**
     * getDislikedMedias check if there are any disliked medias if not it creates a new list.
     * Otherwise it get dislikedMedias from filmsterRepository and returns it.
     * @return the users disliked medias
     */
    public LiveData<List<IMedia>> getDislikedMedias(){
        if(dislikedMedias == null) {
            dislikedMedias = new MutableLiveData<>();
        }
        dislikedMedias.setValue(filmsterRepository.getDislikedMedias());

        return dislikedMedias;
    }

    /**
     * getWatchedMedias check if there are any watched medias if not it creates a new list.
     * Otherwise it get watchedMedias from filmsterRepository and returns it.
     * @return the users watched medias
     */
    public LiveData<List<IMedia>> getWatchedMedias() {
        if(watchedMedias == null){
            watchedMedias = new MutableLiveData<>();
        }
        watchedMedias.setValue(filmsterRepository.getWatchedMedias());
        return watchedMedias;
    }

    /**
     * Exposes a list of all sorting methods to the view
     * @return A LiveData list of ISortMethods for the view to present
     */
    public LiveData<List<ISortMethod>> getSortingMethods() {
        if(sortMethods == null) {
            sortMethods = new MutableLiveData<>();
        }
        sortMethods.setValue(filmsterRepository.getSortMethods());
        return sortMethods;
    }

    /**
     * Receives a String as input from user, forwards it to let the model handle it
     * @param sortMethod A String representing a sortMethod
     */
    public void sortWatchlist(String sortMethod) {
        filmsterRepository.sortWatchlist(sortMethod);
    }

}
