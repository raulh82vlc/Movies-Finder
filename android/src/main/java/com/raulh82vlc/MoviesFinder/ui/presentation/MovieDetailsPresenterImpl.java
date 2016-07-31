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
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMovieDetailsInteractor;
import com.raulh82vlc.MoviesFinder.domain.mapper.MovieDetailsModelDataMapper;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.MovieUI;

import javax.inject.Inject;

/**
 * <p>Implementation of the {@link MovieDetailsPresenter}</p>
 *
 * @author Raul Hernandez Lopez
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private final GetMovieDetailsInteractor interactor;
    private final MovieDetailsModelDataMapper moviesListModelDataMapper;
    private View view;

    @Inject
    MovieDetailsPresenterImpl(
            GetMovieDetailsInteractor interactor,
            MovieDetailsModelDataMapper moviesListModelDataMapper) {
        this.moviesListModelDataMapper = moviesListModelDataMapper;
        this.interactor = interactor;
    }

    @Override
    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("The view should be instantiated");
        }
        this.view = view;
    }

    private void startMovieDetailRequest(String id) {
        interactor.execute(id, new GetMovieDetailsInteractor.MovieDetailsCallback() {
            @Override
            public void onGetMovieDetailOK(Movie movie) {
                final MovieUI movieUI = moviesListModelDataMapper.transform(movie);
                view.loadMovieDetails(movieUI);
            }

            @Override
            public void onGetMovieDetailKO(String error) {
                view.errorGettingMovieDetails(error);
            }
        });
    }

    @Override
    public void start(String id) throws InternetConnectionException {
        startMovieDetailRequest(id);
    }
}
