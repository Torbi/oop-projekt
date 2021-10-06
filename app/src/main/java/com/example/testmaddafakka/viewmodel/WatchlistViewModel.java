package com.example.testmaddafakka.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;

import java.util.List;

public class WatchlistViewModel extends ViewModel {
    private MutableLiveData<IMedia> media;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<List<IMedia>> likedMedias;
    private MutableLiveData<List<IMedia>> dislikedMedias;
    private MutableLiveData<List<IMedia>> watchedMedias;

    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);

    }

    public LiveData<IMedia> getMedia() {
        if (media == null) {
            media = new MutableLiveData<>();
            loadMedias();
        }
        return media;
    }

    public LiveData<List<IMedia>> getLikedMedias(){
        if(likedMedias == null){
            likedMedias = new MutableLiveData<>();
        }
        likedMedias.setValue(filmsterRepository.getLikedMedias());
        return likedMedias;
    }

    public LiveData<List<IMedia>> getDislikedMedias(){
        if(dislikedMedias == null) {
            dislikedMedias = new MutableLiveData<>();
        }
        dislikedMedias.setValue(filmsterRepository.getDislikedMedias());

        return dislikedMedias;
    }
    public LiveData<List<IMedia>> getWatchedMedias(){
        if(watchedMedias == null){
            watchedMedias = new MutableLiveData<>();
        }
        watchedMedias.setValue(filmsterRepository.getWatchedMedias());
        return watchedMedias;
    }


    private void loadMedias() {
        // Do an asynchronous operation to fetch a movie
        media = filmsterRepository.getCurrentMedia();
    }

}
