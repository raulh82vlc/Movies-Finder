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

import android.app.Activity;

import com.raulh82vlc.MoviesFinder.di.scopes.ActivityScope;
import com.raulh82vlc.MoviesFinder.ui.utils.ImageRendering;
import com.raulh82vlc.MoviesFinder.ui.utils.ImageRenderingImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides activity scope context
 * and satisfies both activity & fragment dependencies
 *
 * @author Raul Hernandez Lopez
 */
@Module
public class ActivityModule {
    private final Activity activityContext;

    public ActivityModule(Activity activityContext) {
        this.activityContext = activityContext;
    }

    @Provides
    @ActivityScope
    Activity getActivityContext() {
        return activityContext;
    }

    @Provides
    @ActivityScope
    ImageRendering provideImageRendering(ImageRenderingImpl imageRendering) { return imageRendering; }
}
