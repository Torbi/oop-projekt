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
 * This is a viewModel for the watchlist classes. It has a getMedia method which
 * returns LiveData<IMedia> which the watchlist classes observes to easily update the views.
 * Also provies the subclasses with the liked disliked and watched medias from the
 * FilmsterRepository
 */
public class WatchlistViewModel extends ViewModel {
    private MutableLiveData<IMedia> media;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<List<IMedia>> likedMedias;
    private MutableLiveData<List<IMedia>> dislikedMedias;
    private MutableLiveData<List<IMedia>> watchedMedias;
    private MutableLiveData<List<ISortMethod>> sortMethods;

    /**
     *
     * @param ctx must have context
     */
    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }
    /**
     *
     * @return immutable list of IMeaia
     */
    public LiveData<IMedia> getMedia() {
        if (media == null) {
            media = new MutableLiveData<>();
            loadMedias();
        }
        return media;
    }
    private void loadMedias() {
        // Do an asynchronous operation to fetch a movie
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
    public LiveData<List<IMedia>> getWatchedMedias(){
        if(watchedMedias == null){
            watchedMedias = new MutableLiveData<>();
        }
        watchedMedias.setValue(filmsterRepository.getWatchedMedias());
        return watchedMedias;
    }

    public LiveData<List<ISortMethod>> getSortingMethods() {
        if(sortMethods == null) {
            sortMethods = new MutableLiveData<>();
        }
        sortMethods.setValue(filmsterRepository.getSortMethods());
        return sortMethods;
    }

    public void sortWatchlist(ISortMethod sortMethod) {
        filmsterRepository.sortWatchlist(sortMethod);
    }

}
