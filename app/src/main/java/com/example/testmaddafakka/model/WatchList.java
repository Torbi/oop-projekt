package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WatchList {

    private List<IMedia> watchList;

    public WatchList (){
        this.watchList = new ArrayList<>();
    }

    public void addMedia(IMedia media) {
        System.out.println("added media");
        watchList.add(media);
    }

    public List<IMedia> getLikedList() {
        return watchList
            .stream()
            .filter(m -> m.getState() == MediaState.LIKED)
            .collect(Collectors.toList());
    }

    public List<IMedia> getDislikedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.DISLIKED)
                .collect(Collectors.toList());
    }

    public List<IMedia> getWatchedList() {
        return watchList
                .stream()
                .filter(m -> m.getState() == MediaState.SEEN)
                .collect(Collectors.toList());
    }

}


