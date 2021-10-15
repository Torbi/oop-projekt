package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.IMedia;

import java.util.List;

/**
 * Interface for listening for responses from the adapter
 * @author Torbjörn
 */
public interface IApiListener {

    void update(List<IMedia> movies);
}
