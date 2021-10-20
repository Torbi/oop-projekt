package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

public class DefaultSortingStrategy implements ISortMethod {

    private String name;

    public DefaultSortingStrategy() {
        name = "Default";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return 0;
    }
}
