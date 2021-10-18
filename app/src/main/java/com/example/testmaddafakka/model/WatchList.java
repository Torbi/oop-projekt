package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A watchlist containing all media objects the user has interacted with
 * Can also sort the list by rating and by year, both ascending and descending
 */
public class WatchList {

    private List<IMedia> watchList;

    public WatchList (){
        this.watchList = new ArrayList<>();
    }

    /**
     * Adds a media object to the watchlist,
     * @param media
     */
    public void addMedia(IMedia media) {
        watchList.add(media);
    }

    public List<IMedia> getLikedList() {
        return watchList
            .stream()
            .filter(m -> m.getState() == MediaState.LIKED)
            .collect(Collectors.toList());
    }

    public List<IMedia> getDislikedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.DISLIKED)
                .collect(Collectors.toList());
    }

    public List<IMedia> getWatchedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.SEEN)
                .collect(Collectors.toList());
    }

    public void sortByRating() {
        sort(new SortByRating());
    }

    public void sortByYearAscending() {
        sort(new SortByYearAscending());
    }

    public void sortByYearDescending() {
        sort(new SortByYearDescending());
    }

    private void sort(Comparator<IMedia> method) {
        this.watchList.sort(method);
    }
}


