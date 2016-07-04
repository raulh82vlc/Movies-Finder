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

package com.raulh82vlc.MoviesFinder.ui.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raulh82vlc.MoviesFinder.R;
import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.models.MovieFromListUI;
import com.raulh82vlc.MoviesFinder.ui.activities.MovieDetailsActivity;
import com.raulh82vlc.MoviesFinder.ui.activities.MoviesListActivity;
import com.raulh82vlc.MoviesFinder.ui.adapters.MoviesListAdapter;
import com.raulh82vlc.MoviesFinder.ui.presentation.MoviesListPresenter;
import com.raulh82vlc.MoviesFinder.ui.utils.ImageRendering;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * <p>Movies List Fragment which uses all injected views or components</p>
 * <p>first of all when the activity is created the component,
 * as well as presenter and view injections for each UI element</p>
 *
 * @author Raul Hernandez Lopez
 */
public class MoviesListFragment extends BaseFragment implements MoviesListPresenter.View, MoviesListAdapter.OnItemClickListener {

    private static final String TAG = MoviesListFragment.class.getSimpleName();

    /**
     * UI injections
     */
    @InjectView(R.id.recycler_view)
    public RecyclerView mRecyclerView;
    @InjectView(R.id.no_results_view)
    public TextView mNoResultsTextView;
    /**
     * DI
     */
    @Inject
    MoviesListPresenter presenter;
    @Inject
    ImageRendering imageRendering;

    // UI Widgets
    private MoviesListAdapter mAdapter;
    private MoviesListActivity mActivity;
    /**
     * Data structures
     */
    private List<MovieFromListUI> mMovies = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MoviesListActivity) context;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.component().inject(this);
        presenter.setView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        setRecyclerAdapter();
        setUIWidgetsVisibility(View.VISIBLE, View.GONE);
        mActivity.claimSearchFocus();
    }

    @Override
    public void onDestroyView() {
        mAdapter = null;
        super.onDestroyView();
    }

    /**
     * <p>Sets the adapter and recyclerview</p>
     **/
    private void setRecyclerAdapter() {
        mAdapter = new MoviesListAdapter(imageRendering, mMovies);
        mAdapter.setOnItemClickFromList(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUIWidgetsVisibility(int visible, int gone) {
        mNoResultsTextView.setVisibility(visible);
        mRecyclerView.setVisibility(gone);
    }

    /**
     * <p>Starts the presenter, searching for a specific user's query</p>
     **/
    public void search(String query) {
        try {
            presenter.start(query);
        } catch (InternetConnectionException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        mActivity.showLoaderWithTitleAndDescription(getString(R.string.main_screen),
                getString(R.string.searching));
    }

    @Override
    public void loadedMoviesList(List<MovieFromListUI> moviesList) {
        addMovies(moviesList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRecyclerView.scheduleLayoutAnimation();
        }
        mActivity.hideLoader();
    }

    private void addMovies(List<MovieFromListUI> moviesList) {
        mMovies.clear();
        mMovies.addAll(moviesList);
        mAdapter.notifyDataSetChanged();
        if (moviesList.size() == 0) {
            mNoResultsTextView.setText(getString(R.string.no_results));
            setUIWidgetsVisibility(View.VISIBLE, View.GONE);
        } else {
            mActivity.clearSearchFocus();
            setUIWidgetsVisibility(View.GONE, View.VISIBLE);
        }
    }

    @Override
    public void errorGettingMoviesList(String error) {
        mMovies.clear();
        mAdapter.notifyDataSetChanged();
        mNoResultsTextView.setText(error);
        setUIWidgetsVisibility(View.VISIBLE, View.GONE);
        mActivity.hideLoader();
        // Toast message of Error
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemFromListClick(View view, MovieFromListUI movieFromListUI) {
        /** go to the detail screen */
        MovieDetailsActivity.navigateToDetailsActivity(mActivity, movieFromListUI, view.findViewById(R.id.img_movie));
    }
}
