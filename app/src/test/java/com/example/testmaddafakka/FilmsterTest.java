package com.example.testmaddafakka;

import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilmsterTest {

    private Filmster filmster;

    @Before
    public void beforeTests() {
        IMedia media = new Movie("Cool Movie", "1", "9.1",  "tomcruise.com", "2021");
        IMedia media1 = new Movie("Cool Movie1", "2", "9.1",  "tomcruise.com", "2021");
        IMedia media2 = new Movie("Cool Movie2", "3", "9.1",  "tomcruise.com", "2021");
        IMedia media3 = new Movie("Cool Movie3", "4", "9.1",  "tomcruise.com", "2021");
        IMedia media4 = new Movie("Cool Movie4", "5", "9.1",  "tomcruise.com", "2021");
        List<IMedia> mediaList = new ArrayList<>();
        mediaList.add(media);
        mediaList.add(media1);
        mediaList.add(media2);
        mediaList.add(media3);
        mediaList.add(media4);

        filmster = new Filmster(new User("Guest", "qwerty", new WatchList(), new Preferences()));

        filmster.setMediaList(mediaList);
    }

    @Test
    public void setMediaListAndGetCurrentMedia() {
        assert filmster.getCurrentMedia().getTitle().equals("Cool Movie");
    }

    @Test
    public void nextMediaTest() {
        filmster.nextMedia();
        filmster.nextMedia();
        filmster.nextMedia();
        assert  filmster.getCurrentMedia().getTitle().equals("Cool Movie3");
    }

    //after adding a media to one of the watchlists, nextMedia should be called
    @Test
    public void addLikedMedia() {
        filmster.addLikedMedia(filmster.getCurrentMedia());
        assert filmster.getCurrentMedia().getTitle().equals("Cool Movie1");
    }

    @Test
    public void addDisLikedMedia() {
        filmster.addDislikedMedia(filmster.getCurrentMedia());
        filmster.addDislikedMedia(filmster.getCurrentMedia());
        assert filmster.getCurrentMedia().getTitle().equals("Cool Movie2");
    }

    @Test
    public void addWatchedMedia() {
        filmster.addWatchedMedia(filmster.getCurrentMedia());
        filmster.addWatchedMedia(filmster.getCurrentMedia());
        filmster.addWatchedMedia(filmster.getCurrentMedia());
        assert filmster.getCurrentMedia().getTitle().equals("Cool Movie3");
    }
}
