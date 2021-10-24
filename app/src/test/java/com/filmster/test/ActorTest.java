package com.filmster.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;



import com.filmster.application.model.Actor;
import com.filmster.application.model.MediaState;

import org.junit.Before;
import org.junit.Test;

public class ActorTest {

    private Actor actor;

    @Before
    public void before(){
        actor = new Actor("1337", "Bruce", null);
    }
    @Test
    public void getIDTest() {
        assertEquals("1337", actor.getID());
    }

    @Test
    public void getImageTest() {
        assertNull(actor.getImage());
    }
    @Test
    public void getNameTest() {
        assertEquals("Bruce", actor.getName());
    }
    @Test
    public void getYearTest() {
        assertEquals(0, actor.getYear() );
    }
    @Test
    public void getRatingTest() {
        assertNull(actor.getRating());
    }
    @Test
    public void getStateTest() {
        assertNull(actor.getState());
    }
}

