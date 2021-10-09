package com.example.testmaddafakka;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.testmaddafakka.repository.FilmsterRepository;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

/*
    FilmsterRepository repo;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        //Default test

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        repo = FilmsterRepository.getInstance(appContext);
        assertEquals("com.example.testmaddafakka", appContext.getPackageName());
    }

    @Test
    public void loadMoviesTest() {
        // Checking whether we have got 250 movies

        repo.loadMovies();

        assertEquals(250, repo.getTop250Movies().getValue().size());
    }

    @Test
    public void getCurrentMovieTest() {
        // testing which the current movie is

        repo.getCurrentMovie();

        assertEquals(  "Inception",repo.getCurrentMovie().getValue().getTitle());
    }



    @Test
    public void addLikedMovieTest() {
        // Adding 3 random movies and then checking the size of the list, to see if the LikedList works as expected.


        repo.addLikedMovie(repo.getTop250Movies().getValue().get(3));
        repo.addLikedMovie(repo.getTop250Movies().getValue().get(5));
        repo.addLikedMovie(repo.getTop250Movies().getValue().get(10));

        assertEquals(  3,repo.getLikedMovies().size());
    }

    @Test
    public void addDislikedMovieTest() {
        // // Adding 3 random movies and then checking the size of the list, to see if the DisLikedList works as expected.


        repo.addDislikedMovie(repo.getTop250Movies().getValue().get(3));
        repo.addDislikedMovie(repo.getTop250Movies().getValue().get(5));
        repo.addDislikedMovie(repo.getTop250Movies().getValue().get(10));

        assertEquals(  3,repo.getDislikedMovies().size());
    }



*/
}