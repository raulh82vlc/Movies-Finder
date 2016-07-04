/*
 * Copyright (C) 2016 Raul Hernandez Lopez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raulh82vlc.MoviesFinder.domain.repository;

import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;
import com.raulh82vlc.MoviesFinder.domain.repository.datasources.APIDataSource;

import javax.inject.Inject;

/**
 * Implements {@link MoviesFinderRepository} and allows to have one or more datasources on it
 *
 * @author Raul Hernandez Lopez
 */
public class MoviesFinderRepositoryImpl implements MoviesFinderRepository {

    private APIDataSource apiDataSource;

    @Inject
    MoviesFinderRepositoryImpl(APIDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
    }

    @Override
    public SearchJSONResults getMoviesList(String q) throws InternetConnectionException {
        return apiDataSource.getMoviesList(q);
    }

    @Override
    public Movie getMovie(String id) throws InternetConnectionException {
        return apiDataSource.getMovieDetail(id);
    }
}
