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

package com.raulh82vlc.MoviesFinder.domain.mapper;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;
import com.raulh82vlc.MoviesFinder.domain.models.MovieFromListUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * As a Mapper, means it is a converter between different domains
 *
 * @author Raul Hernandez Lopez
 */
@ActivityScope
public class MoviesListModelDataMapper {

    @Inject
    public MoviesListModelDataMapper() {

    }

    /**
     * Transforms a Collection of {@link SearchJSONResults} into a Collection of {@link SearchJSONResults}.
     *
     * @param searchJSONResults Objects to be transformed.
     * @return List of {@link MovieFromListUI}.
     */
    public List<MovieFromListUI> transform(SearchJSONResults searchJSONResults) {
        if (searchJSONResults != null) {
            List<SearchJSONResults.SearchJSONEntity> searchList = searchJSONResults.getSearchList();
            if (searchList != null && !searchList.isEmpty()) {
                List<MovieFromListUI> moviesUIList = new ArrayList<>();
                for (SearchJSONResults.SearchJSONEntity searchItem : searchJSONResults.getSearchList()) {
                    moviesUIList.add(transform(searchItem));
                }
                return moviesUIList;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Transforms a {@link SearchJSONResults.SearchJSONEntity} into an {@link MovieFromListUI}.
     *
     * @param searchItem Object to be transformed.
     * @return {@link MovieFromListUI}.
     */
    public MovieFromListUI transform(SearchJSONResults.SearchJSONEntity searchItem) {
        if (searchItem == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        MovieFromListUI movieFromListUI = new MovieFromListUI();
        movieFromListUI.setTitle(searchItem.getTitle());
        movieFromListUI.setUrl(searchItem.getImageUrl());
        movieFromListUI.setImdbId(searchItem.getImdbID());
        movieFromListUI.setYear(searchItem.getYear());
        return movieFromListUI;
    }
}
