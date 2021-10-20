package com.filmster.androidTest;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.filmster.application.api.ApiAdapter;
import com.filmster.application.api.ApiListener;
import com.filmster.application.api.IApiListener;
import com.filmster.application.api.parse_buildrequest_strategies.IMDbListBuildRequestStrategy;
import com.filmster.application.model.IMedia;

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
        adapter.setBuildRequestStrategy(new IMDbListBuildRequestStrategy());
        adapter.loadResponse("ls051091770");
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
