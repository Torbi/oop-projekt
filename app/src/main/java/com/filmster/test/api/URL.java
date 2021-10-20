package com.filmster.test.api;

/**
 * Constant values for calling imdb api
 * Could add different api here and implement new strategies for different apis
 * The key can make 5000 calls/day until approximately 23/10 - 2021, then it can make 100 calls/day
 * @author Torbjörn
 */
public enum URL {

    IMDB_URL("https://imdb-api.com/en/API/"),
    IMDB_KEY("/k_ymbjcvxu");

    private final String value;

    private URL(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }
}
