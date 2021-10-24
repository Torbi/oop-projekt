package com.filmster.application.model;

public class MovieStatusItem {
    private String movieId, userId, status;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MovieStatusItem(String movieId, String userId, String status) {
        this.movieId = movieId;
        this.userId = userId;
        this.status = status;
    }
}
