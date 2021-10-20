package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;


/**
 * A comparator for sorting by year from high -> low
 * @author Torbjörn
 */
public class SortByYearDescendingStrategy implements ISortMethod {

    private String name;

    public SortByYearDescendingStrategy() {
        name = "Sort by Descending Years";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compare(IMedia media1, IMedia media2) {
        return ((media2.getYear()) - (media1.getYear()));
    }
}
