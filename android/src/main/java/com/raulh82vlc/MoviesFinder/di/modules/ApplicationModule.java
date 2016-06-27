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

import android.app.Application;

import com.raulh82vlc.MoviesFinder.domain.MainThreadImpl;
import com.raulh82vlc.MoviesFinder.domain.executors.InteractorExecutor;
import com.raulh82vlc.MoviesFinder.domain.executors.MainThread;
import com.raulh82vlc.MoviesFinder.domain.executors.ThreadsPoolExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides application context or generic dependencies.
 *
 * @author Raul Hernandez Lopez
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    InteractorExecutor provideThreadsPoolExecutor(ThreadsPoolExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    MainThread providePostExecutionThread(MainThreadImpl mainThread) {
        return mainThread;
    }
}