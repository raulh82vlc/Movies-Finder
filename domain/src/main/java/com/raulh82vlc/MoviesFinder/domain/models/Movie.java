package com.raulh82vlc.MoviesFinder.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Movie domain model coming from the API
 */
public class Movie {
    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Runtime")
    private String duration;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Language")
    private String language;

    @SerializedName("Plot")
    private String synopsis;

    @SerializedName("Awards")
    private String awards;

    @SerializedName("imdbRating")
    private String rating;

    @SerializedName("imdbVotes")
    private String numVotes;

    @SerializedName("Rated")
    private String ratedForPublic;

    public String getDirector() {
        return director;
    }

    public String getDuration() {
        return duration;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getLanguage() {
        return language;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getAwards() {
        return awards;
    }

    public String getRating() {
        return rating;
    }

    public String getNumVotes() {
        return numVotes;
    }

    public String getRatedForPublic() {
        return ratedForPublic;
    }
}
