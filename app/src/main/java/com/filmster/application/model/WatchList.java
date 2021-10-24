package com.filmster.application.model;

import com.filmster.application.model.sortingstrategies.ISortMethod;
import com.filmster.application.model.sortingstrategies.SortByYearAscendingStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A watchlist containing all media objects the user has interacted with
 * Can also sort the list by rating and by year, both ascending and descending
 * @author Torbjörn
 */
public class WatchList {
    private final List<IMedia> watchList;
    private ISortMethod currentSortingStrategy;
    private List<String> sortingMethods;

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

    /**
     * Returns all medias that are liked
     * @return - A list of IMedia objects
     */
    public List<IMedia> getLikedList() {
        return watchList
            .stream()
            .filter(m -> m.getState() == MediaState.LIKED)
            .collect(Collectors.toList());
    }

    /**
     * Returns all medias that are disliked
     * @return - A list of IMedia objects
     */
    public List<IMedia> getDislikedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.DISLIKED)
                .collect(Collectors.toList());
    }

    /**
     * Returns all medias that are seen
     * @return - A list of IMedia objects
     */
    public List<IMedia> getWatchedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.SEEN)
                .collect(Collectors.toList());
    }

    /**
     * Sets the strategy used for sorting the watchlist
     * @param sortingStrategy - A Comparator that uses IMedias attribute to sort them in some way
     */
    public void setSortingStrategy(ISortMethod sortingStrategy) {
        this.currentSortingStrategy = sortingStrategy;
    }

    /**
     * Uses the wanted sorting strategy to sort the watchlist
     * If no strategy has been chosen, the watchlist will not be sorted
     */
    public void sort() {
        if(this.currentSortingStrategy != null) {
            this.watchList.sort(currentSortingStrategy);
        }
    }
}


