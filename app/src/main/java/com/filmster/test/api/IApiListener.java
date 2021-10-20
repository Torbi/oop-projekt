package com.filmster.test.api;

import com.filmster.test.model.IMedia;

import java.util.List;

/**
 * Interface for listening for responses from the adapter
 * @author Torbjörn
 */
public interface IApiListener {

    void update(List<IMedia> movies);
}
