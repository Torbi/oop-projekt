package com.filmster.application.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.filmster.application.api.IApiListener;
import com.filmster.application.model.ICategory;
import com.filmster.application.api.ApiListener;
import com.filmster.application.api.IApiAdapter;
import com.filmster.application.api.ApiAdapter;
import com.filmster.application.api.parse_buildrequest_strategies.DefaultBuildRequestStrategy;
import com.filmster.application.api.parse_buildrequest_strategies.IMDbListBuildRequestStrategy;
import com.filmster.application.model.Filmster;
import com.filmster.application.model.IMedia;
import com.filmster.application.model.Preferences;
import com.filmster.application.model.User;
import com.filmster.application.model.WatchList;
import com.filmster.application.model.sortingstrategies.ISortMethod;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 *
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private final IApiAdapter imdbAdapter;
    private final MutableLiveData<List<IMedia>> medias;
    private final MutableLiveData<IMedia> currentMedia;
    private final Filmster filmster;
    private final User user;
    private int current = 0;
    private final MutableLiveData<List<ICategory>> categories;

    private FilmsterRepository(Context ctx) {
        this.medias = new MutableLiveData<>();
        this.currentMedia = new MutableLiveData<>();
        this.user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        this.filmster = new Filmster(user);
        this.categories = new MutableLiveData<>();

        ApiListener listener = new ApiListener();
        listener.addListener(this);
        this.imdbAdapter = new ApiAdapter(ctx, listener);

        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public MutableLiveData<IMedia> getCurrentMedia() {
        this.currentMedia.setValue(this.filmster.getCurrentMedia());
        return this.currentMedia;
    }
    private void setMedias(List<IMedia> medias) {
        this.medias.setValue(medias);
        this.filmster.setMediaList(medias);
    }

    @Override
    public void update(List<IMedia> medias) {
        setMedias(medias);
        current = 0;
    }

    /**
     * runs filmster addLikedMedia to set mediaState to LIKED and sets next media.
     * @param media media to like
     */
    public void addLikedMedia(IMedia media) {
        this.filmster.addLikedMedia(media);
        nextMedia();
    }
    /**
     * runs filmster addDisliked to set mediaState to DISLIKED and sets next media.
     * @param media media to disliked
     */
    public void addDislikedMedia(IMedia media) {
        this.filmster.addDislikedMedia(media);
        nextMedia();
    }
    /**
     * runs filmster addWatchedMedia to set mediaState to WATCHED and sets next media.
     * @param media media to watched
     */
    public void addWatchedMedia(IMedia media){
        this.filmster.addWatchedMedia(media);
        nextMedia();
    }

    /**
     * Updates the current media to be displayed to the next
     * If out of objects to display start at the beginning
     * of medias and display them.
     */
    public void nextMedia() {
        this.currentMedia.setValue(this.medias.getValue().get(this.current));
        this.current++;
        if(this.current == this.medias.getValue().size()) {
            this.current = 0;
        }
    }

    /**
     * getter for all the liked medias of an user.
     * @return A list of all liked medias
     */
    public List<IMedia> getLikedMedias(){
        return this.user.getLikedMedia();
    }
    /**
     * getter for all the disliked medias of an user.
     * @return A list of all disliked medias
     */
    public List<IMedia> getDislikedMedias(){
        return this.user.getDislikedMedia();
    }
    /**
     * getter for all the watched medias of an user.
     * @return A list of all watched medias
     */
    public List<IMedia> getWatchedMedias(){
        return this.user.getWatchedMedia();
    }

    /**
     * Checks which strategy should be used depending on if its the default category or not
     * @param categoryName - The name of the category
     */
    public void loadSelectedCategory(String categoryName){
        if(!categoryName.equals("Popular")) {
            this.imdbAdapter.setBuildRequestStrategy(new IMDbListBuildRequestStrategy());
        } else {
            this.imdbAdapter.setBuildRequestStrategy(new DefaultBuildRequestStrategy());
        }
        this.imdbAdapter.loadResponse(getSelectedCategory(categoryName));
    }

    private String getSelectedCategory(String categoryName){
        return this.filmster.currentUsersCategory(categoryName);
    }

    /**
     * Sets a list with movie genres
     * @return A list of movie genres
     */
    public MutableLiveData<List<ICategory>> getCategories() {
        this.categories.setValue(this.filmster.getMovieCategories());
        return this.categories;
    }

    public void search(String name){
        this.imdbAdapter.loadResponse(name);
    }

    public List<ICategory> getSearchResults(){
        return null;
    }

    public void sortWatchlist(ISortMethod sortMethod) {
        this.filmster.sortWatchlist(sortMethod);
    }

    public List<ISortMethod> getSortMethods() {
       return this.filmster.getSortMethods();
    }
}
