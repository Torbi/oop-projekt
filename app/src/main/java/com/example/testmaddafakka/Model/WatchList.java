package com.example.testmaddafakka.Model;

import java.util.ArrayList;
import java.util.List;

public class WatchList {

    private List LikedList = new ArrayList();
    private List DislikedList = new ArrayList();
    private List WatchedList = new ArrayList();


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


