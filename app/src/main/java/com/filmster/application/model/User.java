package com.filmster.application.model;

import java.util.List;

public class User {
    private final String name;
    private final String password;
    private final WatchList watchList;
    private final Preferences Preferences;

    /**
     * A movie object that contains information about a movie
     * @param name - The name of the user
     * @param password - the users password
     * @param watchList - The users watchList (an ArrayList) containing seen, liked or disliked movies
     * @param preferences - A selection of categories
     */

    public User (String name, String password, WatchList watchList, Preferences preferences) {
        this.name = name;
        this.password = password;
        this.watchList = watchList;
        this.Preferences = preferences;
    }

    public void addMedia(IMedia media) {
        watchList.addMedia(media);
    }

    /**
     * Getter for all the liked medias in the users watchlist.
     * @return list of all liked IMedias.
     */
    public List<IMedia> getLikedMedia(){
        return watchList.getLikedList();
    }
    /**
     * Getter for all the disliked medias in the users watchlist.
     * @return list of all disliked IMedias.
     */
    public List<IMedia> getDislikedMedia(){
        return watchList.getDislikedList();
    }
    /**
     * Getter for all the watched medias in the users watchlist.
     * @return list of all watched IMedias.
     */
    public List<IMedia> getWatchedMedia(){
        return watchList.getWatchedList();
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public WatchList getWatchList() {
        return watchList;
    }
    public Preferences getPreferences() {
        return Preferences;
    }
}
