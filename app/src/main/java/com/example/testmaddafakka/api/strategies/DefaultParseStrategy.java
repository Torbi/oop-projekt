package com.example.testmaddafakka.api.strategies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Default strategy for parsing JSONObject responses
 * Presumes JSONObject contains a JSONArray called items
 * @author Torbjörn
 */
public class DefaultParseStrategy implements IParseStrategy {

    @Override
    public List<JsonObject> parseResponse(JSONObject response) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray array = jsonObject.getAsJsonArray("items");

        List<JsonObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            jsonObjects.add((JsonObject) array.get(i));
        }
        return jsonObjects;
    }
}
