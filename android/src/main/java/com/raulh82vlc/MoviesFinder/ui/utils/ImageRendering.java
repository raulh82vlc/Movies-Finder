package com.raulh82vlc.MoviesFinder.ui.utils;

import android.widget.ImageView;

/**
 * Image Rendering contract
 */
public interface ImageRendering {
    /**
     * load an image
     *
     * @param urlPath path of the image from the API
     * @param img     ImageView where to render
     */
    void loadImage(String urlPath, ImageView img);
}
