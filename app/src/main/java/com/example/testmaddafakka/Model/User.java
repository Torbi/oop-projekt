package com.example.testmaddafakka.Model;

import android.provider.Settings;

import java.util.ArrayList;

public class User {

    private String name;
    private String password;
    private ArrayList watchList;
    private Settings Settings;
    private Preferences Preferences;




    public User (String name, String password, ArrayList watchList, Settings Settings, Preferences preferences) {
        this.name = name;
        this.password = password;
        this.watchList = watchList;
        this.Settings = Settings;
        this.Preferences = Preferences;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList getWatchList() {
        return watchList;
    }

    public Settings getSettings() {
        return Settings;
    }

    public Preferences getPreferences() {
        return Preferences;
    }
}
