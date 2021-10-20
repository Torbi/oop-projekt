package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

import java.util.Comparator;


/**
 * A comparator for sorting by year from high -> low
 * @author Torbjörn
 */
public class SortByYearDescendingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia media1, IMedia media2) {
        return ((media2.getYear()) - (media1.getYear()));
    }
}
