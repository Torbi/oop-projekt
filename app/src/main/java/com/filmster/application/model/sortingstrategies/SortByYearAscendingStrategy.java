package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

import java.util.Comparator;

/**
 * A comparator for sorting by year from low -> high
 * @author Torbjörn
 */
public class SortByYearAscendingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia media1, IMedia media2) {
        return ((media1.getYear()) - (media2.getYear()));
    }
}
