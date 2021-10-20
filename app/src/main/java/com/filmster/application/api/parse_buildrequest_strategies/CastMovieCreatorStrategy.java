package com.filmster.application.api.parse_buildrequest_strategies;


import com.filmster.application.model.IMedia;
import com.filmster.application.model.Movie;
import com.google.gson.JsonObject;

public class CastMovieCreatorStrategy{
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
