package com.filmster.application.api;

import com.filmster.application.model.Actor;
import com.filmster.application.model.IMedia;
import com.google.gson.JsonObject;

public class SearchResultFactory implements IMediaFactory{
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Actor(object.get("name").toString(),
                    object.get("id").toString(),
                    object.get("image").toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
