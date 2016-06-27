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

package com.raulh82vlc.MoviesFinder;

import android.app.Application;

import com.raulh82vlc.MoviesFinder.di.components.ApplicationComponent;
import com.raulh82vlc.MoviesFinder.di.modules.ApplicationModule;
import com.raulh82vlc.MoviesFinder.di.components.DaggerApplicationComponent;

/**
 * {@link ApplicationComponent} could be used to provide dependencies needed by the whole app
 * execution. Application context linked dependencies would be exposed by it too.
 *
 * @author Raul Hernandez Lopez
 */
public class MoviesFinderApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}
