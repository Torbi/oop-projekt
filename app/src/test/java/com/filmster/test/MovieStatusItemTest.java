package com.filmster.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.filmster.application.model.Actor;
import com.filmster.application.model.MovieStatusItem;

import org.junit.Before;
import org.junit.Test;

public class MovieStatusItemTest {
    private MovieStatusItem movieStatusItem;

    @Before
    public void before(){
        movieStatusItem = new MovieStatusItem("1", "2", "3");
    }
    @Test
    public void movieTest(){
        movieStatusItem.setUserId("hello");
        assertEquals("hello", movieStatusItem.getUserId());
    }
    @Test
    public void statusTest() {
        movieStatusItem.setStatus("hej");
        assertEquals("hej", movieStatusItem.getStatus());
    }
    @Test
    public void userIdTest() {
        movieStatusItem.setMovieId("elite");
        assertEquals("elite", movieStatusItem.getMovieId());
    }
}

