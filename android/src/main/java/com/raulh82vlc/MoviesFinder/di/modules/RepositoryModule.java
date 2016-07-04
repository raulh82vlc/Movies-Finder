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
import com.raulh82vlc.MoviesFinder.domain.api.WebServicesApiCalls;
import com.raulh82vlc.MoviesFinder.domain.api.WebServicesApiCallsImpl;
import com.raulh82vlc.MoviesFinder.domain.datasources.APIDataSourceImpl;
import com.raulh82vlc.MoviesFinder.domain.repository.MoviesFinderRepository;
import com.raulh82vlc.MoviesFinder.domain.repository.MoviesFinderRepositoryImpl;
import com.raulh82vlc.MoviesFinder.domain.repository.datasources.APIDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * <p>Repository module which provides the different specific implementations as
 * well as the {@link APIDataSource} which retrieves info from the network</p>
 *
 * @author Raul Hernandez Lopez
 */

@Module
public class RepositoryModule {
    @Provides
    @ActivityScope
    MoviesFinderRepository provideMoviesRepository(MoviesFinderRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @ActivityScope
    APIDataSource provideAPIDataSource(APIDataSourceImpl dataSource) {
        return dataSource;
    }

    @Provides
    @ActivityScope
    WebServicesApiCalls provideWebServicesApiCalls(WebServicesApiCallsImpl webServicesApiCalls) {
        return webServicesApiCalls;
    }
}
