package com.example.testmaddafakka.model;

import java.util.Comparator;

/**
 * A comparator for sorting the watchlist by rating, from high -> low rating
 * @author Torbjörn
 */
public class SortByRating implements Comparator<IMedia> {
    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return (int)((t1.getRating()) - (iMedia.getRating()));
    }
}
