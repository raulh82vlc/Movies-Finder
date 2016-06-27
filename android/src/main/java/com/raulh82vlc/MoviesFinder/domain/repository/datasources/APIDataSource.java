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

package com.raulh82vlc.MoviesFinder.domain.repository.datasources;


import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;

/**
 * <p>Source of retrieving info from the API</p>
 *
 * @author Raul Hernandez Lopez
 */
public interface APIDataSource {

    /**
     * Gets a Search results with movies list from the API
     * @param query user query
     */
    SearchJSONResults getMoviesList(String query) throws InternetConnectionException;

    /**
     * Gets a Recipe from the API
     * @param id identifier of the recipe
     */
    Movie getMovieDetail(String id) throws InternetConnectionException;
}
