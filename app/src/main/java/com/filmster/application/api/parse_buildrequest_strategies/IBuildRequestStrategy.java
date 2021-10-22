package com.filmster.application.api.parse_buildrequest_strategies;


/**
 * Interface for different strategies for building
 * requests to a remote api
 * @author Torbjörn
 */
public interface IBuildRequestStrategy {

    /**
     * Builds a specific request to be used when making a request to a remote api
     * @param request - A String that is part of the request
     * @return - A String representing the request
     */
    String buildRequest(String request);
}
