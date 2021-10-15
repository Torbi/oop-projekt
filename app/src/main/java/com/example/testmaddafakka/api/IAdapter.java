package com.example.testmaddafakka.api;

import com.example.testmaddafakka.api.strategies.IBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IParseStrategy;

/**
 * Methods that can be made to get different results from the api
 * And change the different strategies for the adapter
 * @author Torbjörn
 */
public interface IAdapter {

    void getList(String request);

    void setParseStrategy(IParseStrategy strategy);

    void setBuildRequestStrategy(IBuildRequestStrategy strategy);

}
