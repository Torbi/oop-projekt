package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.IMedia;

import java.util.List;

public interface IApiListener {

    public void update(List<IMedia> movies);
}
