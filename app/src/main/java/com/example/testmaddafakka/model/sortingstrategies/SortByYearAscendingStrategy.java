package com.example.testmaddafakka.model.sortingstrategies;

import com.example.testmaddafakka.model.IMedia;

import java.util.Comparator;

/**
 * A comparator for sorting by year from low -> high
 * @author Torbjörn
 */
public class SortByYearAscendingStrategy implements Comparator<IMedia> {
    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return (int)((iMedia.getYear()) - (t1.getYear()));
    }
}
