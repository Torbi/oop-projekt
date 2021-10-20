package com.filmster.application.api;

import com.filmster.application.model.IMedia;
import com.google.gson.JsonObject;

/**
 * Interface for MediaFactory for creating media objects from json
 * @author Torbjörn
 */
public interface IMediaFactory {
    IMedia createMediaObjectFromJson(JsonObject object);
}
