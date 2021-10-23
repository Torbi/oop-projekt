package com.filmster.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;



import com.filmster.application.model.Actor;

import org.junit.Before;
import org.junit.Test;

public class ActorTest {

    private Actor actor;

    @Before
    public void before(){
        actor = new Actor("1337", "Bruce", null);
    }
    @Test
    public void getID() {
        assertEquals("1337", actor.getID());
    }

    @Test
    public void getImage() {
        assertNull(actor.getImage());
    }
    @Test
    public void getName() {
        assertEquals("Bruce", actor.getName());
    }
}

