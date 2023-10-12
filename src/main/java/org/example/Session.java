package org.example;

import java.io.Serializable;

public class Session implements Serializable{

    private int session;

    private double hour;

    private Theater theatre;

    private Movie movie;

    public Session(int session, double hour,Movie movie, Theater theatre) {

        this.session = session;
        this.hour = hour;
        this.movie = movie;
        this.theatre = theatre;

    }

    public int getSession() {
        return session;
    }

    public double getHour() {
        return hour;
    }

    public Theater getTheatre() {
        return theatre;
    }

    public Movie getMovie() {
        return movie;
    }
}

