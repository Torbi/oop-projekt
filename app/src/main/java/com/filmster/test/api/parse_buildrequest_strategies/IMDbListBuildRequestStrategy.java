package com.filmster.test.api.parse_buildrequest_strategies;


import com.filmster.test.api.URL;

/**
 *Uses the IMDbList option to get a list from IMDb
 *The request has to be a valid listid from IMDb, starts with ls
 * in format url/IMDbList/key/listID
 *@author Torbjörn
 */
public class IMDbListBuildRequestStrategy implements IBuildRequestStrategy {

    /**
     * Build a request to get a IMDbList.
     * @param request - a valid list id from imdb, starts with ls
     * @return A correct request in the form of a String, url/IMDbList/key/listID
     */
    @Override
    public String buildRequest(String request) {
        return URL.IMDB_URL.getValue() + "IMDbList" + URL.IMDB_KEY.getValue() + "/" + request;
    }
}
