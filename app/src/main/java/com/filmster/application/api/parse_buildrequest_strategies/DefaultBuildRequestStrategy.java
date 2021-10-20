package com.filmster.application.api.parse_buildrequest_strategies;

import com.filmster.application.api.URL;

/**
 * Default strategy for building requests, url/request/key
 * Possible values for request is atm Top250Movies,
 * @author Torbjörn
 */
public class DefaultBuildRequestStrategy implements IBuildRequestStrategy {

    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + request + URL.IMDB_KEY.getValue();
    }
}
