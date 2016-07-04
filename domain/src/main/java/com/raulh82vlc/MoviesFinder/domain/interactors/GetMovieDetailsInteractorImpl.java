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
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.repository.MoviesFinderRepository;

import javax.inject.Inject;

/**
 * Implementation of the Get specific details of a movie
 *
 * @author Raul Hernandez Lopez
 */
public class GetMovieDetailsInteractorImpl implements GetMovieDetailsInteractor, Interactor {

    private InteractorExecutor executor;
    private MainThread mainThread;
    private MoviesFinderRepository repository;
    private MoviDetailsCallback callback;
    private String id;

    @Inject
    GetMovieDetailsInteractorImpl(InteractorExecutor executor,
                                  MainThread mainThread,
                                  MoviesFinderRepository repository) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.repository = repository;
    }

    @Override
    public void execute(String id, MoviDetailsCallback callback) {
        this.id = id;
        this.callback = callback;
        try {
            this.executor.run(this);
        } catch (InternetConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() throws InternetConnectionException {
        String errorMessage = "no listing from network";
        try {
            Movie movie = repository.getMovie(id);
            if (movie != null) {
                notifyMovieDetailProcessed(movie);
            } else {
                notifyError(errorMessage);
            }
        } catch (InternetConnectionException e) {
            notifyError(e.getMessage());
            throw new InternetConnectionException(e.getMessage());
        }
    }

    /**
     * <p>Notifies to the UI (main) thread the corresponding callback with a corresponding movie detail</p>
     */
    private void notifyMovieDetailProcessed(final Movie movie) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMovieDetailOK(movie);
            }
        });
    }

    /**
     * <p>Notifies to the UI (main) thread that an error has happened</p>
     */
    private void notifyError(final String error) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMovieDetailKO(error);
            }
        });
    }
}
