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

import android.app.Activity;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.di.modules.ActivityModule;

import dagger.Component;

/**
 * Those components linked to the activity lifecycle or
 * to the activity context will depend or extend this one.
 * Those which are lifecycles or mainly common dependencies linked to activity context
 * would be the kind of dependencies were are talking about.
 * <p/>
 * Activity-level components should extend this component.
 * Fragment components may depend on this component.
 *
 * @author Raul Hernandez Lopez
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface AbstractActivityComponent {
    /**
     * Expose the activity to sub-graphs.
     */
    Activity activityContext();
}