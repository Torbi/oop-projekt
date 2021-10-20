package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

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
