package com.filmster.test;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.MediaState;
import com.filmster.application.model.Movie;
import com.filmster.application.model.Preferences;
import com.filmster.application.model.User;
import com.filmster.application.model.WatchList;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;
    IMedia media;
    IMedia media1;
    IMedia media2;

    @Before
    public void createUserAndMedia() {

        media =new Movie("Cool Movie", "1", 9.1,  "tomcruise.com", 2021);
        media1 = new Movie("Cool Movie1", "2", 9.1,  "tomcruise.com", 2021);
        media2 = new Movie("Cool Movie2", "3", 9.1, "tomcruise.com", 2021);

        user = new User("Guest", "qwerty", new WatchList(), new Preferences());
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
