package com.raulh82vlc.MoviesFinder.domain.models;

/**
 * Movie adapted to UI needs
 *
 * @author Raul Hernandez Lopez
 */
public class MovieUI {

    private String synopsis, rating, votes, actors;
    private String awards, director, duration, writer, ratedForQualifiedPublic;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String tDirector) {
        this.director = tDirector;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRatedForQualifiedPublic() {
        return ratedForQualifiedPublic;
    }

    public void setRatedForQualifiedPublic(String ratedForQualifiedPublic) {
        this.ratedForQualifiedPublic = ratedForQualifiedPublic;
    }
}
