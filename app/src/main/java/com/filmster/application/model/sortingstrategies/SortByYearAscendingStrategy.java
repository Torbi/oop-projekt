package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

/**
 * A comparator for sorting by year from low -> high
 * @author Torbjörn
 */
public class SortByYearAscendingStrategy implements ISortMethod {

    private String name;

    public SortByYearAscendingStrategy() {
        name = "Sort by Ascending Years";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compare(IMedia media1, IMedia media2) {
        return ((media1.getYear()) - (media2.getYear()));
    }
}
