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
package com.raulh82vlc.MoviesFinder.domain.interactors;

import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.executors.Interactor;
import com.raulh82vlc.MoviesFinder.domain.executors.InteractorExecutor;
import com.raulh82vlc.MoviesFinder.domain.executors.MainThread;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;
import com.raulh82vlc.MoviesFinder.domain.repository.MoviesFinderRepository;

import javax.inject.Inject;

/**
 * Implementation of the Get Movies List Interactor
 *
 * @author Raul Hernandez Lopez
 */
public class GetMoviesListInteractorImpl implements GetMoviesListInteractor, Interactor {

    private InteractorExecutor executor;
    private MainThread mainThread;
    private MoviesFinderRepository repository;
    private GetMoviesListCallback callback;
    private String query;

    @Inject
    GetMoviesListInteractorImpl(InteractorExecutor executor,
                                MainThread mainThread,
                                MoviesFinderRepository repository) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.repository = repository;
    }

    @Override
    public void execute(String query, GetMoviesListCallback callback) throws InternetConnectionException {
        this.query = query;
        this.callback = callback;
        this.executor.run(this);
    }

    @Override
    public void run() throws InternetConnectionException {
        String errorMessage = "no listing from network";
        try {
            SearchJSONResults moviesList = (SearchJSONResults) repository.getMoviesList(query);
            if (moviesList != null) {
                notifySuccessfullyLoaded(moviesList);
            } else {
                notifyError(errorMessage);
            }
        } catch (InternetConnectionException e) {
            notifyError(e.getMessage());
            throw new InternetConnectionException(e.getMessage());
        }
    }

    /**
     * <p>Notifies to the UI (main) thread the result of the request,
     * and sends a callback with a list</p>
     */
    private void notifySuccessfullyLoaded(final SearchJSONResults moviesList) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMoviesListOK(moviesList);
            }
        });
    }

    /**
     * <p>Notifies to the UI (main) thread that an error happened</p>
     */
    private void notifyError(final String error) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMoviesListKO(error);
            }
        });
    }
}
