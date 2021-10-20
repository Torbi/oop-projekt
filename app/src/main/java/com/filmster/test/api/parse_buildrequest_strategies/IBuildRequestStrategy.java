package com.filmster.test.api.parse_buildrequest_strategies;


/**
 * Interface for different strategies for building
 * requests to a remote api
 * @author Torbjörn
 */
public interface IBuildRequestStrategy {

    String buildRequest(String request);
}
