package com.raulh82vlc.MoviesFinder.domain.api;

import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;

/**
 */
public interface WebServicesApiCalls {

        /**
         * Gets a Search results with movies list from the API
         * @param query user query
         */
        SearchJSONResults getMoviesList(String query);

        /**
         * Gets a Recipe from the API
         * @param id identifier of the recipe
         */
        Movie getMovieDetail(String id);
}
