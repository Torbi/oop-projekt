package com.filmster.application.api.parse_buildrequest_strategies;

import com.filmster.application.api.URL;

/**
 *Uses the IMDbName option to get a list of movies with one specific actor or director
 *The request has to be a name in format url/Name/key/id
 *@author Albin Sundström
 */


public class IMDbNameBuildRequestStrategy implements IBuildRequestStrategy {

    /**
     * Build a request to get a list of movies with one specific actor or director.
     * @param request - a name of an actor or director
     * @return A correct request in the form of a String, url/Name/key/id
     * "" needs to be removed to make a correct request.
     */
    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + "Name" + URL.IMDB_KEY.getValue() + "/" + request.replace("\"", "");
    }
}
