package org.example;

import java.io.Serializable;

public class Movie implements Serializable{
    private String movie;
    private String director;
    private int year;

    public Movie(String movie, String director, int year) {
        this.movie = movie;
        this.director = director;
        this.year = year;
    }

    public String getMovie() {
        return movie;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

}
