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

package com.raulh82vlc.MoviesFinder.di.modules;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMoviesListInteractor;
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMoviesListInteractorImpl;
import com.raulh82vlc.MoviesFinder.ui.presentation.MoviesListPresenter;
import com.raulh82vlc.MoviesFinder.ui.presentation.MoviesListPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides all user required artifacts
 * (presenter, interactors, repository, datasources or mappers)
 * in order to use them in a decoupled way
 *
 * @author Raul Hernandez Lopez
 */
@Module
public class MoviesListModule {

    @Provides
    @ActivityScope
    MoviesListPresenter provideMoviesListPresenter(MoviesListPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    GetMoviesListInteractor provideGetMoviesListInteractor(GetMoviesListInteractorImpl interactor) {
        return interactor;
    }
}
