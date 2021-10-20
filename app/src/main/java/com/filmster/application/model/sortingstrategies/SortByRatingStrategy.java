package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

import java.util.Comparator;

/**
 * A comparator for sorting the watchlist by rating, from high -> low rating
 * @author Torbjörn
 */
public class SortByRatingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return (int)((t1.getRating()) - (iMedia.getRating()));
    }
}
