package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.repository.FilmsterRepository;


/**
 * The viewModel for the MainView Fragment, the viewModel observes the LiveData objects
 * And updates itself when they are changed
 * The mainView updates the MainViewModel on input from the user
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<IMedia> media;
    private FilmsterRepository filmsterRepository;

    public void init(Context ctx) {
        if(media != null) {
            return;
        }
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        //filmsterRepository.loadMedias();
        filmsterRepository.loadSelectedCategory("Popular");
    }

    public IMedia getCurrentMedia(){
        return media.getValue();
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

    public void addLikedMedia() {
        filmsterRepository.addLikedMedia(getCurrentMedia());
        nextMedia();
    }
    public void addDislikedMedia() {
        filmsterRepository.addDislikedMedia(getCurrentMedia());
        nextMedia();
    }
    public void addWatchedMedia(){
        filmsterRepository.addWatchedMedia(getCurrentMedia());
        nextMedia();
    }
    public void nextMedia() {
        filmsterRepository.nextMedia();
    }
    public String checkMovieLength(String title){
        if(title.length() > 15){
            return title.substring(0, 16) + "...";
        }
        return title;
    }
    public String shorten(String text){
        return text.substring(1, text.length()-1);
    }

}
