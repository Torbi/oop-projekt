package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;

public class WatchList {

    private List<IMedia> likedList;
    private List<IMedia> dislikedList;
    private List<IMedia> watchedList;

    public WatchList (){
        this.likedList = new ArrayList<>();
        this.dislikedList = new ArrayList<>();
        this.watchedList = new ArrayList<>();
    }

    public void addLikedMedia(IMedia media) {
       this.likedList.add(media);
    }


    public void addDislikedMedia(IMedia media) {
        this.dislikedList.add(media);
        if(likedList.contains(media)){
            likedList.remove(media);
        }
    }


    public void addWatchedMedia(IMedia media) {
        this.watchedList.add(media);
    }


    public List<IMedia> getLikedList() {
        return likedList;
    }

    public List<IMedia> getDislikedList() {
        return dislikedList;
    }

    public List<IMedia> getWatchedList() {
        return watchedList;
    }
}


