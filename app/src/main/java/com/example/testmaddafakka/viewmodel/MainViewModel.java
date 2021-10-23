package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;


/**
 * The viewModel for the MainView Fragment, the viewModel observes the LiveData objects
 * And updates itself when they are changed
 * The mainView updates the MainViewModel on input from the user
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<IMedia> media;
    private FilmsterRepository filmsterRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init(Context ctx) {
        if(media != null) {
            return;
        }
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        filmsterRepository.loadMedias();
      //  filmsterRepository.setDataFromFirebase();
    }

    /**
     * The method that the mainView observes for changes
     * Calls an asynchronous method that loads the movie
     * @return an immutable liveData Movie object
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

    public void addLikedMedia(IMedia media) {
        filmsterRepository.addLikedMedia(media);
        nextMedia();
    }
    public void addDislikedMedia(IMedia media) {
        filmsterRepository.addDislikedMedia(media);
        nextMedia();
    }
    public void addWatchedMedia(IMedia media){
        filmsterRepository.addWatchedMedia(media);
        nextMedia();
    }
    public void nextMedia() {
        filmsterRepository.nextMedia();
    }
}
