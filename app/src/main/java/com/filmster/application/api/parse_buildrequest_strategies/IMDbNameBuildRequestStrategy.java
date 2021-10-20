package com.filmster.application.api.parse_buildrequest_strategies;

import com.filmster.application.api.URL;

/**
 *Uses the IMDbNameSearch option to get a list of actors/directors from IMDb
 *The request has to be a name in format url/SearchName/key/name
 *@author Albin Sundström
 */

public class IMDbNameBuildRequestStrategy implements IBuildRequestStrategy{
    /**
     * Build a request to get a list of actors/directors.
     * @param request - a name of an actor or director
     * @return A correct request in the form of a String, url/SearchName/key/name
     */
    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + "SearchName" + URL.IMDB_KEY.getValue() + "/" + request;
    }
}
