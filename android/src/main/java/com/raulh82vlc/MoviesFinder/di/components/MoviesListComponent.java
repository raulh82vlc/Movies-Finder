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

package com.raulh82vlc.MoviesFinder.di.components;

import com.raulh82vlc.MoviesFinder.di.modules.ActivityModule;
import com.raulh82vlc.MoviesFinder.di.modules.MoviesListModule;
import com.raulh82vlc.MoviesFinder.di.modules.RepositoryModule;
import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.ui.activities.MoviesListActivity;
import com.raulh82vlc.MoviesFinder.ui.fragments.MoviesListFragment;

import dagger.Component;

/**
 * RequestsComponent is the main container of dependencies
 * linked to the activity context. Moreover, this component extends
 * {@link AbstractActivityComponent}, therefore the activity context
 * is provided from the abstract parent component.
 *
 * @author Raul Hernandez Lopez
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                RepositoryModule.class,
                MoviesListModule.class
        })
public interface MoviesListComponent extends AbstractActivityComponent {

    void inject(MoviesListActivity moviesListActivity);

    void inject(MoviesListFragment moviesListFragment);
}
