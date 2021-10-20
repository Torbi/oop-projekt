package com.filmster.test.api.parse_buildrequest_strategies;

import com.filmster.test.api.URL;

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