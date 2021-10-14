package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.IMedia;

import java.util.List;

/**
 * Interface for listening for responses from the adapter
 */
public interface IApiListener {

    public void update(List<IMedia> movies);
}
