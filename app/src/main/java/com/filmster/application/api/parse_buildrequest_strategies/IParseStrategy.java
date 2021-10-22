package com.filmster.application.api.parse_buildrequest_strategies;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

/**
 * Interface different strategies for parsing JSONObject responses
 * @author Torbjörn
 */
public interface IParseStrategy {
    /**
     * Parses a JSONObject into a list of gsons JsonObject
     * Because they are easier to work with and less prone to error
     * @param response - A JSONObject
     * @return - A list of JsonObject
     */
    List<JsonObject> parseResponse (JSONObject response);
}
