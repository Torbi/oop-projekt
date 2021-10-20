package com.filmster.test;

import com.filmster.test.model.IMedia;
import com.filmster.test.model.MediaState;
import com.filmster.test.model.Movie;
import com.filmster.test.model.WatchList;
import com.filmster.test.model.sortingstrategies.SortByRatingStrategy;
import com.filmster.test.model.sortingstrategies.SortByYearAscendingStrategy;
import com.filmster.test.model.sortingstrategies.SortByYearDescendingStrategy;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class WatchlistTest {

    private WatchList watchList;

    @Before
    public void createWatchlist() {
        IMedia media = new Movie("Cool Movie", "1", 9.0,  "tomcruise.com", 1999);
        IMedia media1 = new Movie("Cool Movie1", "2", 10.0,  "tomcruise.com", 1985);
        IMedia media2 = new Movie("Cool Movie2", "3", 6.2,  "tomcruise.com", 2015);
        IMedia media3 = new Movie("Cool Movie3", "4", 1.0,  "tomcruise.com", 2010);
        IMedia media4 = new Movie("Cool Movie4", "5", 3.2,  "tomcruise.com", 2002);
        IMedia media5 = new Movie("Cool Movie4", "6", 8.7,  "tomcruise.com", 2018);

        media.setState(MediaState.SEEN);
        media1.setState(MediaState.DISLIKED);
        media2.setState(MediaState.LIKED);
        media3.setState(MediaState.DISLIKED);
        media4.setState(MediaState.LIKED);
        media5.setState(MediaState.SEEN);

        watchList = new WatchList();
        watchList.addMedia(media);
        watchList.addMedia(media1);
        watchList.addMedia(media2);
        watchList.addMedia(media3);
        watchList.addMedia(media4);
        watchList.addMedia(media5);
    }

    @Test
    public void getSeenMoviesTest() {
        assert watchList.getWatchedList().size() == 2;
    }

    @Test
    public void getLikedMoviesTest() {
        assert watchList.getLikedList().size() == 2;
    }

    @Test
    public void getDislikedMoviesTest() {
        assert watchList.getDislikedList().size() == 2;
    }

    @Test
    public void sortByRatingTest() {
        watchList.setSortingStrategy(new SortByRatingStrategy());
        watchList.sort();
        List<IMedia> likedlist = watchList.getLikedList();
        assertTrue(((likedlist.get(0).getRating()) > (likedlist.get(1).getRating())));
    }

    @Test
    public void sortByYearAscendingTest() {
        watchList.setSortingStrategy(new SortByYearAscendingStrategy());
        watchList.sort();
        List<IMedia> likedlist = watchList.getLikedList();
        assertTrue(((likedlist.get(0).getYear()) < (likedlist.get(1).getYear())));
    }

    @Test
    public void sortByYearDescendingTest() {
        watchList.setSortingStrategy(new SortByYearDescendingStrategy());
        watchList.sort();
        List<IMedia> likedlist = watchList.getLikedList();
        assertTrue(((likedlist.get(0).getYear()) > (likedlist.get(1).getYear())));
    }

}
