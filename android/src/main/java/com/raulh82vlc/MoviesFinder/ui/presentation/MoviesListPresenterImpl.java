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

package com.raulh82vlc.MoviesFinder.ui.presentation;

import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMoviesListInteractor;
import com.raulh82vlc.MoviesFinder.domain.mapper.MoviesListModelDataMapper;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;
import com.raulh82vlc.MoviesFinder.domain.models.MovieFromListUI;

import java.util.List;

import javax.inject.Inject;

/**
 * Implementation of the {@link MoviesListPresenter}
 *
 * @author Raul Hernandez Lopez
 */
public class MoviesListPresenterImpl implements MoviesListPresenter {

    private final GetMoviesListInteractor interactorGetMoviesList;
    private final MoviesListModelDataMapper moviesListModelDataMapper;
    private View view;

    @Inject
    MoviesListPresenterImpl(
            GetMoviesListInteractor interactorGetMoviesList,
            MoviesListModelDataMapper moviesListModelDataMapper) {
        this.interactorGetMoviesList = interactorGetMoviesList;
        this.moviesListModelDataMapper = moviesListModelDataMapper;
    }

    @Override
    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("The view should be instantiated");
        }
        this.view = view;
    }

    private void startGetMoviesList(String query) throws InternetConnectionException {
        interactorGetMoviesList.execute(
                query,
                new GetMoviesListInteractor.GetMoviesListCallback() {
            @Override
            public void onGetMoviesListOK(SearchJSONResults searchJSONResults) {
                final List<MovieFromListUI> catsList = moviesListModelDataMapper.transform(searchJSONResults);
                view.loadedMoviesList(catsList);
            }

            @Override
            public void onGetMoviesListKO(String error) {
                view.errorGettingMoviesList(error);
            }
        });
    }

    @Override
    public void start(String key) throws InternetConnectionException {
        startGetMoviesList(key);
    }
}
