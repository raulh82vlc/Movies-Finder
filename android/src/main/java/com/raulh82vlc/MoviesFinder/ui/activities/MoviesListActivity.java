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

package com.raulh82vlc.MoviesFinder.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.raulh82vlc.MoviesFinder.R;
import com.raulh82vlc.MoviesFinder.MoviesFinderApp;
import com.raulh82vlc.MoviesFinder.di.components.MoviesListComponent;
import com.raulh82vlc.MoviesFinder.di.modules.ActivityModule;
import com.raulh82vlc.MoviesFinder.di.components.DaggerMoviesListComponent;
import com.raulh82vlc.MoviesFinder.ui.fragments.MoviesListFragment;

import butterknife.ButterKnife;

/**
 * <p>Movies List Finder by a User query</p>
 *
 * @author Raul Hernandez Lopez
 */
public class MoviesListActivity extends BaseActivity {

    private MoviesListComponent mMoviesListComponent;
    private MoviesListFragment mMoviesListFragment;
    private SearchView mSearchView;

    public MoviesListComponent component() {
        if (mMoviesListComponent == null) {
            mMoviesListComponent = DaggerMoviesListComponent.builder()
                    .applicationComponent(((MoviesFinderApp) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mMoviesListComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        component().inject(this);
        ButterKnife.inject(this);

        mMoviesListFragment = (MoviesListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_movies_list);
        setToolbarInitialisation();
        setInitialTitle();
    }

    @Override
    protected void setInitialTitle() {
        mToolbar.setTitle(getString(R.string.main_screen));
    }

    @Override
    protected void setToolbarInitialisation() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        mToolbar.inflateMenu(R.menu.menu_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Get the SearchView and set the searchable configuration
        final MenuItem searchItem = mToolbar.getMenu().findItem(R.id.action_search);
        mToolbar.setFocusable(true);
        setSearchView(searchManager, searchItem);
    }

    private void setSearchView(SearchManager searchManager, MenuItem searchItem) {
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (mSearchView != null) {
            mSearchView.setIconified(false);
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String actualQuery) {
                    if (mMoviesListFragment != null) {
                        mMoviesListFragment.search(actualQuery);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            });
            mSearchView.setFocusable(true);
        }
    }

    @Override
    protected void onDestroy() {
        mMoviesListFragment = null;
        super.onDestroy();
    }

    public void clearSearchFocus() {
        if (mSearchView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isAcceptingText()) {
                imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
                mSearchView.clearFocus();
            }
        }
    }

    public void claimSearchFocus() {
        if (mSearchView != null) {
            mSearchView.setFocusable(true);
        }
    }
}
