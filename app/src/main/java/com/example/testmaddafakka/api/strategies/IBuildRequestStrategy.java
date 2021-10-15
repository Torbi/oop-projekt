package com.example.testmaddafakka.api.strategies;


/**
 * Interface for different strategies for building
 * requests to a remote api
 * @author Torbjörn
 */
public interface IBuildRequestStrategy {

    String buildRequest(String request);
}
