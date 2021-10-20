package com.filmster.androidTest;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.filmster.test.api.ApiListener;
import com.filmster.test.api.IApiListener;
import com.filmster.test.model.IMedia;
import com.filmster.test.api.ApiAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class IMDbApiAdapterTest implements IApiListener {

    private List<IMedia> mediaList;

    @Before
    public void createAdapter() {
        ApiListener listener = new ApiListener();
        listener.addListener(this);
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ApiAdapter adapter = new ApiAdapter(appContext, listener);
        adapter.getList("Top250Movies");
        //looks ugly and feels wrong, but it works
        try {
            Thread.sleep(2000);
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
        assert mediaList.size() == 250;
    }
}
