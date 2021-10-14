package com.example.testmaddafakka.api;

import com.example.testmaddafakka.api.strategies.IBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IParseStrategy;

/**
 * Methods that can be made to get different results from the api
 * And change the different strategies for the adapter
 */
public interface IAdapter {

    public void getList(String request);

    public void setParseStrategy(IParseStrategy strategy);

    public void setBuildRequestStrategy(IBuildRequestStrategy strategy);

}
