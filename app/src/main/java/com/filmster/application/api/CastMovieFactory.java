package com.filmster.application.api;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.Movie;
import com.google.gson.JsonObject;

public class CastMovieFactory implements IMediaFactory{
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Movie(object.get("title").toString(),
                    object.get("id").toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
