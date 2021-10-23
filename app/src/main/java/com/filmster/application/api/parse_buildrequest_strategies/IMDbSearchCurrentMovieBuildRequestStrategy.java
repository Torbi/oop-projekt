package com.filmster.application.api.parse_buildrequest_strategies;


import com.filmster.application.api.URL;

/**
 *Uses the IMDbTitle option to get a single title
 *The request has to be a valid ID starting with tt
 * in format url/Title/key/id
 *@author Albin Sundström
 */


public class IMDbSearchCurrentMovieBuildRequestStrategy implements IBuildRequestStrategy{

    /**
     * Build a request to get a single title.
     * @param request - the id of a specific title starting with tt
     * @return A correct request in the form of a String, url/Title/key/id
     * "" needs to be removed to make a correct request.
     */
    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + "Title" + URL.IMDB_KEY.getValue() + "/" + request.replace("\"", "");
    }
}
