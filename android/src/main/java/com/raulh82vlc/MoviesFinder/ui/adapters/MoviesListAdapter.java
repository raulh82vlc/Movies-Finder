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

package com.raulh82vlc.MoviesFinder.ui.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raulh82vlc.MoviesFinder.R;
import com.raulh82vlc.MoviesFinder.domain.models.MovieFromListUI;
import com.raulh82vlc.MoviesFinder.ui.utils.ImageRendering;
import com.raulh82vlc.MoviesFinder.ui.viewholders.MovieViewHolder;

import java.util.List;

/**
 * Adapter for movies listing
 */
public class MoviesListAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    private List<MovieFromListUI> mMovies;

    private ImageRendering mImageLoader;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            if (mOnItemClickListener != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mOnItemClickListener.onItemFromListClick(view, (MovieFromListUI) view.getTag());
                    }
                }, 200);
            }
        }
    };

    public MoviesListAdapter(ImageRendering imageLoader, List<MovieFromListUI> movies) {
        mImageLoader = imageLoader;
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View aItemListView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_movie, parent, false);
        MovieViewHolder myViewHolder = new MovieViewHolder(aItemListView);
        setListeners(myViewHolder);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, int position) {
        final MovieFromListUI movieItem = mMovies.get(position);
        movieViewHolder.mName.setText(movieItem.getTitle());
        mImageLoader.loadImage(
                movieItem.getUrl(),
                movieViewHolder.mImage);
        movieViewHolder.itemView.setTag(movieItem);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    /**
     * Sets the corresponding listeners
     * for a click {@link View.OnClickListener} to our {@link MovieViewHolder} item
     *
     * @param movieViewHolder {@link MovieViewHolder}
     */
    private void setListeners(final MovieViewHolder movieViewHolder) {
        if (mOnItemClickListener != null) {
            movieViewHolder.itemView.setOnClickListener(mOnClickListener);
        }
    }

    public void setOnItemClickFromList(OnItemClickListener onItemClickFromList) {
        mOnItemClickListener = onItemClickFromList;
    }

    public interface OnItemClickListener {
        void onItemFromListClick(View iView, MovieFromListUI movieFromListUI);
    }
}
