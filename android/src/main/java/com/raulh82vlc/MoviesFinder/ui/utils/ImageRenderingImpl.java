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

package com.raulh82vlc.MoviesFinder.ui.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raulh82vlc.MoviesFinder.R;

import javax.inject.Inject;

/**
 * Image Rendering
 *
 * @author Raul Hernandez Lopez
 */
public class ImageRenderingImpl implements ImageRendering {

    private RequestManager mGlideInstance;

    @Inject
    public ImageRenderingImpl(Activity context) {
        // this might not lead to a null pointer exception but just in case re-initialisation
        if (mGlideInstance == null) {
            synchronized (this) {
                if (mGlideInstance == null) {
                    mGlideInstance = Glide.with(context);
                }
            }
        }
    }

    @Override
    public void loadImage(String urlPath, ImageView img) {
        mGlideInstance
                .load(urlPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(img);
    }
}
