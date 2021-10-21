package com.filmster.test;

import com.filmster.application.model.ICategory;
import com.filmster.application.model.Preferences;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PreferencesTest {

    private Preferences preferences;

    @Before
    public void createPreferences() {
        preferences = new Preferences();
    }

    @Test
    public void getMovieGenresTest() {
        List<ICategory> genres = preferences.getMovieGenres();
        //16 is the current number of genres available
        assert genres.size() == 16;
    }

    @Test
    public void getMatchingCategoryTest() {
        String genre = "Biography";
        ICategory category = preferences.getMatchingGenre(genre);
        assert category.getName().equals(genre);
    }
}
