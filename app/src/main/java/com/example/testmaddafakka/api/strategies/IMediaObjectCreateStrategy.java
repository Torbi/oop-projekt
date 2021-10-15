package com.example.testmaddafakka.api.strategies;

import com.example.testmaddafakka.model.IMedia;
import com.google.gson.JsonObject;

/**
 * Interface for different strategies for creating media objects from json
 * @author Torbjörn
 */
public interface IMediaObjectCreateStrategy {

    public IMedia createMediaObjectFromJson(JsonObject object);
}
