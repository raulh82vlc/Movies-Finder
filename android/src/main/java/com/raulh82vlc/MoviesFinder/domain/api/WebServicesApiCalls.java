package com.raulh82vlc.MoviesFinder.domain.api;

/**
 * Web services API calls contract
 *
 * @author Raul Hernandez Lopez
 */
public interface WebServicesApiCalls<L, M> {
    /**
     * Gets a Search results with movies list from the API
     *
     * @param query user query
     */
    L getMoviesList(String query);

    /**
     * Gets a Movie from the API
     *
     * @param id identifier of the movie
     */
    M getMovieDetail(String id);
}
