package com.example.testmaddafakka;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.testmaddafakka.api.ApiAdapter;
import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.api.strategies.IMDbListBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbListParseStrategy;
import com.example.testmaddafakka.model.IMedia;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ApiAdapterStrategyTest implements IApiListener {

    private List<IMedia> mediaList;

    @Before
    public void createAdapter() {
        ApiListener listener = new ApiListener();
        listener.addListener(this);
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ApiAdapter adapter = new ApiAdapter(appContext, listener);
        adapter.setParseStrategy(new IMDbListParseStrategy());
        adapter.setBuildRequestStrategy(new IMDbListBuildRequestStrategy());
        adapter.getList("ls051091770");
        //looks ugly and feels wrong, but it works
        try {
            Thread.sleep(17000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(List<IMedia> movies) {
        mediaList = movies;
    }

    @Test
    public void hasMovies() {
        assert mediaList.size() > 0;
    }
}
