package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.IMDbApiAdapter;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.model.IMedia;
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
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 0;
    private MutableLiveData<List<ICategory>> categories;


    private FilmsterRepository(Context ctx) {

        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<List<ICategory>>();

        imdbAdapter = new IMDbApiAdapter(ctx, listener);
        loadMedias();
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }

    public void loadMedias() {
        imdbAdapter.get250Movies();
    }

    public MutableLiveData<IMedia> getCurrentMedia() {
        this.currentMedia.setValue(filmster.getCurrentMedia());
        return this.currentMedia;
    }

    private void setMedias(List<IMedia> medias) {
        this.medias.setValue(medias);
        filmster.setMediaList(medias);
    }

    @Override
    public void update(List<IMedia> medias) {
        setMedias(medias);
    }

    public void addLikedMedia(IMedia media) {
        filmster.addLikedMedia(media);
        nextMedia();
    }

    public void addDislikedMedia(IMedia media) {
        filmster.addDislikedMedia(media);
        nextMedia();
    }
    public void addWatchedMedia(IMedia media){
        filmster.addWatchedMedia(media);
        nextMedia();
    }

    public void nextMedia() {
        //listener.notifyListeners(this.movies.getValue().get(current));
        //ERRRRORORORORORROR
        this.currentMedia.setValue(this.medias.getValue().get(current));
        current++;
    }
    public List<IMedia> getLikedMedias(){
        return user.getLikedMedia();
    }
    public List<IMedia> getDislikedMedias(){
        return user.getDislikedMedia();
    }
    public List<IMedia> getWatchedMedias(){
        return user.getWatchedMedia();
    }


    public void loadSelectedCategory(String categoryName){
        imdbAdapter.getList(getSelectedCategory(categoryName));
    }

    public String getSelectedCategory(String categoryName){
        return filmster.CurrentUsersCategory(categoryName);
    }

    /**
     * Sets a list with movie genres
     * @return A list of movie genres
     */
    public MutableLiveData<List<ICategory>> getCategories() {
        this.categories.setValue(filmster.getMovieCategories());
        return this.categories;
    }

}
