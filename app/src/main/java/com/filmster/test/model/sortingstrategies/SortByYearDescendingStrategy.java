package com.filmster.test.model.sortingstrategies;

import com.filmster.test.model.IMedia;

import java.util.Comparator;


/**
 * A comparator for sorting by year from high -> low
 * @author Torbjörn
 */
public class SortByYearDescendingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return (int)((t1.getYear()) - (iMedia.getYear()));
    }
}
