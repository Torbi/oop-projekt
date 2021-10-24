package com.filmster.application.api.parse_buildrequest_strategies;

import com.filmster.application.api.URL;

/**
 *Uses the IMDbSearchName option to get a list of actors or directors
 *The request has to be a name in format url/SearchName/key/id
 *@author Albin Sundström
 */

public class IMDbSearchNameBuildRequestStrategy implements IBuildRequestStrategy{
    /**
     * Build a request to get a list of movies with one specific actor or director.
     * @param request - a name of an actor or director
     * @return A correct request in the form of a String, url/SearchName/key/id
     * The string needs to be split to get the correct result from the api
     */
    @Override
    public String buildRequest(String request) {
        String[] strings = request.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return URL.IMDB_URL.getValue() + "SearchName" + URL.IMDB_KEY.getValue() + "/" + sb;
    }


}
