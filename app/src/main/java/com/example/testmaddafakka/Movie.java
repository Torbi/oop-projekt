package com.example.testmaddafakka;

public class Movie {

    private String title;
    private String information;
    private String rating;
    private String starring;
    private String image;
    private int id;

    public Movie(String title, String information, String rating, String starring, String image) {
        this.title = title;
        this.information = information;
        this.rating = rating;
        this.starring = starring;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

}
