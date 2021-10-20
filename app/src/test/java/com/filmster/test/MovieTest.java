package com.filmster.test;

import com.filmster.test.model.Movie;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

    private Movie movie;

    @Before
    public void createMovie() {
        movie = new Movie("Inception", "1", 10.0,  "imageurl", 1337);
    }

    @Test
    public void getTitle() {
        assert movie.getName().equals("Inception");
    }

    @Test
    public void getRating() {
        assert movie.getRating() == 10;
    }

    @Test
    public void getImage() {
        assert movie.getImage().equals("imageurl");
    }

    @Test
    public void getYear() {
        assert movie.getYear() == 1337;
    }

}
