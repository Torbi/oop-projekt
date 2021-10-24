package com.filmster.test;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.MediaState;
import com.filmster.application.model.Movie;
import com.filmster.application.model.Preferences;
import com.filmster.application.model.User;
import com.filmster.application.model.WatchList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {

    private User user;
    private IMedia media;

    @Before
    public void createUserAndMedia() {

        media =new Movie("Cool Movie", "1", 9.1,  "tomcruise.com", 2021);
        user = new User("Guest", "qwerty", new WatchList(), new Preferences());
    }

    @Test
    public void getPreferencesTest(){
        System.out.println(user.getPreferences().getMovieGenres().get(0).getName());
        assert (user.getPreferences().getMovieGenres().get(0).getName().equals("Popular"));
    }
    @Test
    public void genreIdTest(){
        System.out.println(user.getPreferences().getMovieGenres().get(0).getID());
        assert (user.getPreferences().getMovieGenres().get(0).getID().equals("Top250Movies"));
    }
    @Test
    public void addLikedMediaTest() {
        user.addMedia(media);
        media.setState(MediaState.LIKED);
        assert user.getLikedMedia().get(0).getName().equals("Cool Movie");
    }

    @Test
    public void addDislikedMediaTest() {
        user.addMedia(media);
        media.setState(MediaState.DISLIKED);
        assert user.getDislikedMedia().get(0).getName().equals("Cool Movie");
    }

    @Test
    public void addWatchedMediaTest() {
        user.addMedia(media);
        media.setState(MediaState.SEEN);
        assert user.getWatchedMedia().get(0).getName().equals("Cool Movie");
    }

    @Test
    public void getNameTest() {
        assert user.getName().equals("Guest");
    }

    @Test
    public void getPassTest() {
        assert user.getPassword().equals("qwerty");
    }
}
