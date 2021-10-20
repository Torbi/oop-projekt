package com.filmster.application.api.parse_buildrequest_strategies;


import com.filmster.application.api.URL;

/**
 *Uses the IMDbNameSearch option to get a list of actors/directors from IMDb
 *The request has to be a name in format url/SearchName/key/name
 *@author Albin Sundström
 */


public class IMDbSearchCurrentMovieBuildRequestStrategy implements IBuildRequestStrategy{

    /**
     * Build a request to get a movie.
     * @param request - the id of a specific movie
     * @return A correct request in the form of a String, url/Title/key/name
     */
    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + "Title" + URL.IMDB_KEY.getValue() + "/" + request;
    }
}
