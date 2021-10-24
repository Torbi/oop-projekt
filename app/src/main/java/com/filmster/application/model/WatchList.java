package com.filmster.application.model;

import com.filmster.application.model.sortingstrategies.DefaultSortingStrategy;
import com.filmster.application.model.sortingstrategies.ISortMethod;
import com.filmster.application.model.sortingstrategies.SortByRatingStrategy;
import com.filmster.application.model.sortingstrategies.SortByYearAscendingStrategy;
import com.filmster.application.model.sortingstrategies.SortByYearDescendingStrategy;

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
    private List<ISortMethod> sortMethods;

    public WatchList (){
        this.watchList = new ArrayList<>();

        initSortMethods();
    }

    public List<ISortMethod> getSortMethods() {
        return sortMethods;
    }

    public void sortWatchlist(String sortMethod) {
        ISortMethod method = new DefaultSortingStrategy();
        for(int i = 0; i < this.sortMethods.size(); i++) {
            if(sortMethod.equals(this.sortMethods.get(i).getName())) {
                method = this.sortMethods.get(i);
                break;
            }
        }
        setSortingStrategy(method);
        sort();
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
    private void setSortingStrategy(ISortMethod sortingStrategy) {
        this.currentSortingStrategy = sortingStrategy;
    }

    /**
     * Uses the wanted sorting strategy to sort the watchlist
     * If no strategy has been chosen, the watchlist will not be sorted
     */
    private void sort() {
        if(this.currentSortingStrategy != null) {
            this.watchList.sort(currentSortingStrategy);
        }
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


