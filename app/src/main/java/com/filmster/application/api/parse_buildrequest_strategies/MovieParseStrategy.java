package com.filmster.application.api.parse_buildrequest_strategies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for parsing JSONObject responses
 * @author Albin Sundström
 */

public class MovieParseStrategy implements IParseStrategy{

    public MovieParseStrategy(){
    }

    @Override
    public List<JsonObject> parseResponse(JSONObject response) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

        List<JsonObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(jsonObject);

        return jsonObjects;
    }
}
