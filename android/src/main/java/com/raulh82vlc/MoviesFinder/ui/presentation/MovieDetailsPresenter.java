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

package com.raulh82vlc.MoviesFinder.ui.presentation;

import com.raulh82vlc.MoviesFinder.domain.models.MovieUI;

/**
 * <p>Presenter responsible of asking for movie details and get them back if any available</p>
 *
 * @author Raul Hernandez Lopez
 */
public interface MovieDetailsPresenter extends Presenter {

    void setView(View view);

    interface View {
        void loadMovieDetails(MovieUI movieUI);

        void errorGettingMovieDetails(String error);
    }
}
