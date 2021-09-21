package com.example.testmaddafakka;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class IMDbAdapterTest {



    @Test
    public void getMovie() {
        IAdapter adapter = new IMDbApiAdapter();
        List<Movie> list;
        list = adapter.getMovies();
        System.out.println(list.get(0).getTitle() + " title");
        assertTrue(list.size() > 100);
    }
}
