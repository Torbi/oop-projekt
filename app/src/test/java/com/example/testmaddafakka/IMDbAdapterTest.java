package com.example.testmaddafakka;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.testmaddafakka.Model.IAdapter;
import com.example.testmaddafakka.Model.IMDbApiAdapter;
import com.example.testmaddafakka.Model.Movie;

import java.util.List;

public class IMDbAdapterTest {



    @Test
    public void getMovie() {
        IAdapter adapter = new IMDbApiAdapter();
        List<Movie> list;
        list = adapter.getMovies();
        assertEquals(250, list.size());
    }
}
