package com.example.testmaddafakka.api;


/**
 * Interface for different strategies for building
 * requests to a remote api
 * @author Torbjörn
 */
public interface IBuildRequestStrategy {

    public String buildRequest(String request);
}
