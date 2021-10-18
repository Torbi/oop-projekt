package com.example.testmaddafakka.model;

import java.util.Comparator;


/**
 * A comparator for sorting by year from high -> low
 * @author Torbjörn
 */
public class SortByYearDescending implements Comparator<IMedia> {
    @Override
    public int compare(IMedia iMedia, IMedia t1) {
        return (int)((t1.getYear()) - (iMedia.getYear()));
    }
}
