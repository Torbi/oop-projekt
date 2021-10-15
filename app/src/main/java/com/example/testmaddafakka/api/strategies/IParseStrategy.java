package com.example.testmaddafakka.api.strategies;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

/**
 * Interface different strategies for parsing JSONObject responses
 * @author Torbjörn
 */
public interface IParseStrategy {

    List<JsonObject> parseResponse (JSONObject response);
}
