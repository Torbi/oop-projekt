package com.filmster.test.api;

import com.filmster.test.model.IMedia;

import java.util.List;

/**
 * Interface for callbacks when volley requests have been made
 * @author Torbjörn
 */
public interface VolleyCallback {

    void onSuccess(List<IMedia> media);
}
