package com.raulh82vlc.MoviesFinder.domain.mapper;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.MovieUI;

import javax.inject.Inject;

/**
 * Mapper conversion from Movie model of the API to Movie model of the UI
 *
 * @author Raul Hernandez Lopez
 */
@ActivityScope
public class MovieDetailsModelDataMapper {

    @Inject
    public MovieDetailsModelDataMapper() {

    }

    /**
     * Transforms a {@link Movie} into an {@link MovieUI}.
     */
    public MovieUI transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
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
