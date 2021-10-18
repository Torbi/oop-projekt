package com.example.testmaddafakka.api;

import com.example.testmaddafakka.api.strategies.IBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMediaFactory;
import com.example.testmaddafakka.api.strategies.IParseStrategy;

/**
 * Methods that can be made to get different results from the api
 * And change the different strategies for the adapter
 * @author Torbjörn
 */
public interface IAdapter {

    /**
     * The method to call for making a request to an api
     * To get a result, the class that makes the call needs to listen to the
     * result using ApiListener
     * @param request - A correct request
     */
    void getList(String request);

    /**
     * A strategy to parse different responses from the api
     * @param strategy - A strategy to parse the response
     */
    void setParseStrategy(IParseStrategy strategy);

    /**
     * A strategy to build different requests to an api
     * @param strategy - A strategy to build the request
     */
    void setBuildRequestStrategy(IBuildRequestStrategy strategy);

    /**
     * A factory to create media objects
     * @param factory - A mediaFactory
     */
    void setMediaFactory(IMediaFactory factory);

}
