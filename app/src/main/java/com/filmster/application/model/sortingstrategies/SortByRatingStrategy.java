package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

/**
 * A comparator for sorting the watchlist by rating, from high -> low rating
 * @author Torbjörn
 */
public class SortByRatingStrategy implements ISortMethod {

    private String name;

    public SortByRatingStrategy() {
        name = "Sort by Rating";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compare(IMedia media1, IMedia media2) {
        return (int)((media2.getRating()) - (media1.getRating()));
    }
}
