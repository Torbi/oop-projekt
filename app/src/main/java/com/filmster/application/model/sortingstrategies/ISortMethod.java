package com.filmster.application.model.sortingstrategies;

import com.filmster.application.model.IMedia;

import java.util.Comparator;

/**
 * Interface for sorting Strategies, also to present the name of the sorting methods in the view
 * @author Torbjörn
 */
public interface ISortMethod extends Comparator<IMedia>{

    String getName();
}
