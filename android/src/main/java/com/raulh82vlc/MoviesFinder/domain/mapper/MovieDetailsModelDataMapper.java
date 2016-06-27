package com.raulh82vlc.MoviesFinder.domain.mapper;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.MovieUI;

import javax.inject.Inject;

/**
 * Mapper conversion
 */
@ActivityScope
public class MovieDetailsModelDataMapper {

    @Inject
    public MovieDetailsModelDataMapper() {

    }

    public MovieUI transform(Movie movie) {
        MovieUI movieUI = new MovieUI();
        movieUI.setSynopsis(movie.getSynopsis());
        movieUI.setVotes(movie.getNumVotes());
        movieUI.setRating(movie.getRating());
        movieUI.setActors(movie.getActors());
        movieUI.setAwards(movie.getAwards());
        movieUI.setDirector(movie.getDirector());
        movieUI.setDuration(movie.getDuration());
        movieUI.setWriter(movie.getWriter());
        movieUI.setRatedForQualifiedPublic(movie.getRatedForPublic());
        return movieUI;
    }
}
