package com.example.testmaddafakka;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.api.IMDbApiAdapter;

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
        IMDbApiAdapter adapter = new IMDbApiAdapter(appContext, listener);
        adapter.get250Movies();
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