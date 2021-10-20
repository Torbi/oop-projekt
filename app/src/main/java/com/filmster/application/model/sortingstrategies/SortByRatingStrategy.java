package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

import java.util.Comparator;

/**
 * A comparator for sorting the watchlist by rating, from high -> low rating
 * @author Torbjörn
 */
public class SortByRatingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia media1, IMedia media2) {
        return (int)((media2.getRating()) - (media1.getRating()));
    }
}
