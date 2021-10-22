package com.filmster.application.api;

import com.filmster.application.model.IMedia;

import java.util.List;

/**
 * Interface for callbacks when volley requests have been made
 * @author Torbjörn
 */
public interface VolleyCallback {
    /**
     * Callback function for ApiAdapter
     * @param media - A List of IMediaObjects
     */
    void onSuccess(List<IMedia> media);
}
