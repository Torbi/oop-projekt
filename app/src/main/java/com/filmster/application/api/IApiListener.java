package com.filmster.application.api;

import com.filmster.application.model.IMedia;

import java.util.List;

/**
 * Interface for listening for responses from the adapter
 * @author Torbjörn
 */
public interface IApiListener {
    void update(List<IMedia> movies);
}
