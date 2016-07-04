package com.raulh82vlc.MoviesFinder.domain.api;

import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;

/**
 * Web services API calls contract
 *
 * @author Raul Hernandez Lopez
 */
public interface WebServicesApiCalls {
    /**
     * Gets a Search results with movies list from the API
     *
     * @param query user query
     */
    SearchJSONResults getMoviesList(String query);

    /**
     * Gets a Movie from the API
     *
     * @param id identifier of the movie
     */
    Movie getMovieDetail(String id);
}
