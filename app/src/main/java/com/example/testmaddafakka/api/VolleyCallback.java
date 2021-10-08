package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;

import java.util.List;

/**
 * Interface for callbacks when volley requests have been made
 */
public interface VolleyCallback {

    public void onSuccess(List<IMedia> media);
}
