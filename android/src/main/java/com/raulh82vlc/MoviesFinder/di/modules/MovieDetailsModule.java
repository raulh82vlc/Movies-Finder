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

import com.raulh82vlc.MoviesFinder.ui.presentation.MovieDetailsPresenter;
import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMovieDetailsInteractor;
import com.raulh82vlc.MoviesFinder.domain.interactors.GetMovieDetailsInteractorImpl;
import com.raulh82vlc.MoviesFinder.ui.presentation.MovieDetailsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <p>Module with all related presenters and interactors for Movie Details</p>
 *
 * @author Raul Hernandez Lopez
 */
@Module
public class MovieDetailsModule {

    @Provides
    @ActivityScope
    MovieDetailsPresenter provideMovieDetailsPresenter(MovieDetailsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    GetMovieDetailsInteractor provideGetMovieDetailsInteractor(GetMovieDetailsInteractorImpl interactor) {
        return interactor;
    }
}

