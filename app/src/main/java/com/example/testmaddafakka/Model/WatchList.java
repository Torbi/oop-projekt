package com.example.testmaddafakka.Model;

import java.util.ArrayList;
import java.util.List;

public class WatchList {

    private List LikedList = new ArrayList();
    private List DislikedList = new ArrayList();
    private List WatchedList = new ArrayList();

    /**
     * @param LikedList    - An ArrayList that contains the movies the User has liked
     * @param DislikedList - An ArrayList that contains the movies the User has disliked
     * @param WatchedList  - An ArrayList that contains the movies the User has already seen

    */


    public WatchList (List LikedList, List DislikedList, List WatchedList){


        this.LikedList = LikedList;
        this.DislikedList = DislikedList;
        this.WatchedList = WatchedList;
    }




    public List getLikedList() {
        return LikedList;
    }

    public List getDislikedList() {
        return DislikedList;
    }

    public List getWatchedList() {
        return WatchedList;

    }
}


