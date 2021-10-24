package com.filmster.test;

import com.filmster.application.model.MediaState;
import com.filmster.application.model.Movie;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

    private Movie movie;
    private Movie movie2;
    private Movie movie1;

    @Before
    public void createMovie() {
        movie = new Movie("Inception", "1", 10.0,  "imageurl", 1337);
        movie1 = new Movie("ste", "123");
        movie2 = new Movie("test", "test", 10.0, "test", "imgur", 1337);
    }
    @Test
    public void getStarring(){
        assert movie2.getID().equals("test");
    }

    @Test
    public void getMovieID(){
        assert movie1.getID().equals("123");
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
