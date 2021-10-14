package com.example.testmaddafakka.api.strategies;

import com.example.testmaddafakka.model.IMedia;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

/**
 * Interface different strategies for parsing JSONObject responses
 */
public interface IParseStrategy {

    public List<JsonObject> parseResponse (JSONObject response);
}
