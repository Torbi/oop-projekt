package com.example.testmaddafakka.api;

/**
 * Constant values for calling imdb api
 * Could add different api here and implement new strategies for different apis
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
