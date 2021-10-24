package com.filmster.application.model;

import android.widget.ArrayAdapter;

import com.filmster.application.model.sortingstrategies.DefaultSortingStrategy;
import com.filmster.application.model.sortingstrategies.ISortMethod;
import com.filmster.application.model.sortingstrategies.SortByRatingStrategy;
import com.filmster.application.model.sortingstrategies.SortByYearAscendingStrategy;
import com.filmster.application.model.sortingstrategies.SortByYearDescendingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Filmster keeps track of all movies, users and currentMovies displayed on the screen
 * @author Torbjörn, Albin Sundström, Magnus
 */
public class Filmster{
    private List<IMedia> mediaList;
    private final User user;
    private int currentMediaCounter;
    private final List<ICategory> categoryList;
    private List<ISortMethod> sortMethods;
    private List<IMedia> resultList;
    private List<IMedia> castMovies;

    /**
     * Constructor for filmster, initializes some data and receives a user for the program
     * @param user A user
     */

    public Filmster(User user) {
        this.user = user;
        this.currentMediaCounter = 0;
        this.mediaList = new ArrayList<>();
        this.categoryList = new ArrayList<>();
        this.resultList = new ArrayList<>();
        this.castMovies = new ArrayList<>();

        //a fake movie is added to give time for imdbapiadapter to get real movies from the api
        this.mediaList.add(new Movie("Inception", "tt1375666", 9.9, "https://imdb-api.com/Images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6791_AL_.jpg",2010));
        initSortMethods();
    }

    /**
     * Setter for MediaList
     * When new IMedia objects are received, resets the counter
     * @param moviesList A List of IMedia objects
     */
    public void setMediaList(List<IMedia> moviesList) {
        this.mediaList = moviesList;
        this.currentMediaCounter = 0;
    }
    public List<IMedia> getMediaList(){
        return mediaList;
    }

    /**
     * Returns the currently displayed IMedia object
     * @return - The currently displayed IMedia object
     */
    public IMedia getCurrentMedia() {
       return mediaList.get(currentMediaCounter);
   }

    /**
     * Increases the currentMediaCounter by 1 to signal which IMedia is next in line to be currentMedia
     */
    public void nextMedia() {
      currentMediaCounter++;
   }

    /**
     * Returns the id of a chosen object
     * @param pos - The position of a chosen object
     * @return - The id of the object at position
     */
    public String getChosenID(int pos){
        return resultList.get(pos).getID();
    }

    /**
     * Setter for resultList
     * @param results - A list of IMedia objects
     */
    public void setResultList(List<IMedia> results){
        this.resultList = results;
    }

    /**
     * Getter for resultList
     * @return - A list of IMedia objects
     */
    public List<IMedia> getResultList(){
        return resultList;
    }

    /**
     * Getter for castMovies
     * @return - A list of IMedia objects
     */
    public List<IMedia> getCastMovies(){
        return castMovies;
    }

    /**
     * Setter for castMovies
     * @param moviesList - A list of IMedia objects
     */
    public void setCastMovieList(List<IMedia> moviesList) {
        this.castMovies = moviesList;
    }

    /**
     * Sets the state of the current media object to liked and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addLikedMedia(IMedia media) {
        media.setState(MediaState.LIKED);
        this.user.addMedia(media);
        nextMedia();
    }

    /**
     * Sets the state of the current media object to disliked and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addDislikedMedia(IMedia media) {
        media.setState(MediaState.DISLIKED);
        this.user.addMedia(media);
        nextMedia();
    }

    /**
     * Sets the state of the current media object to seen and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addWatchedMedia(IMedia media){
        media.setState(MediaState.SEEN);
        this.user.addMedia(media);
        nextMedia();
    }

    /**
     * Returns the current Users chosen Category
     * @param categoryName - The name of the category chosen
     * @return - The id of the category chosen
     */
    public String currentUsersCategory(String categoryName){
        ICategory category = user.getPreferences().getMatchingGenre(categoryName);
        return category.getID();
    }

    /**
     * Collects categories
     * @return A list of categories
     */
    public List<ICategory> getMovieCategories() {
        int listSize = user.getPreferences().getMovieGenres().size();
        if(categoryList.isEmpty()){
            for(int i = 0; i < listSize; i++){
                categoryList.add(user.getPreferences().getMovieGenres().get(i));
            }
        }
        return categoryList;
    }

    /**
     * Gets the current users watchlist and gives it a sorting strategy and orders it to sort itself
     * @param sortMethod - An ISortMethod
     */
    public void sortWatchlist(String sortMethod) {
        ISortMethod method = new DefaultSortingStrategy();
        for(int i = 0; i < this.sortMethods.size(); i++) {
            if(sortMethod.equals(this.sortMethods.get(i).getName())) {
                method = this.sortMethods.get(i);
                break;
            }
        }
        this.user.getWatchList().setSortingStrategy(method);
        this.user.getWatchList().sort();
    }

    /**
     * Returns all available sorting methods
     * @return - A list of available sorting methods
     */
    public List<ISortMethod> getSortMethods() {
        return this.sortMethods;
    }

    /**
     * All available sorting methods are initiated here, later given to the view to present
     */
    private void initSortMethods() {
        this.sortMethods = new ArrayList<>();
        this.sortMethods.add(new DefaultSortingStrategy());
        this.sortMethods.add(new SortByRatingStrategy());
        this.sortMethods.add(new SortByYearAscendingStrategy());
        this.sortMethods.add(new SortByYearDescendingStrategy());
    }
 }
