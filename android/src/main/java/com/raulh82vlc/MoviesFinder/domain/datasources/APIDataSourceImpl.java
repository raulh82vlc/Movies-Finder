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

package com.raulh82vlc.MoviesFinder.domain.datasources;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.raulh82vlc.MoviesFinder.R;
import com.raulh82vlc.MoviesFinder.domain.api.WebServicesApiCallsImpl;
import com.raulh82vlc.MoviesFinder.domain.models.Movie;
import com.raulh82vlc.MoviesFinder.domain.models.SearchJSONResults;
import com.raulh82vlc.MoviesFinder.domain.repository.datasources.APIDataSource;
import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;

import javax.inject.Inject;

import retrofit.RetrofitError;

/**
 * {@link APIDataSource} Implementation
 *
 * @author Raul Hernandez Lopez
 */
public class APIDataSourceImpl implements APIDataSource {

    /**
     * Vars declaration
     */
    private Activity mContext;
    private WebServicesApiCallsImpl mWebServicesApiCallsImpl;

    @Inject
    APIDataSourceImpl(Activity context,
                      WebServicesApiCallsImpl webServicesApiCallsImpl) {
        mContext = context;
        mWebServicesApiCallsImpl = webServicesApiCallsImpl;
    }

    @Override
    public SearchJSONResults getMoviesList(String query) throws InternetConnectionException {
        return startMovieFromListRequest(query);
    }

    @Override
    public Movie getMovieDetail(String id) throws InternetConnectionException {
        return startMovieRequest(id);
    }

    /**
     * Starts Movies List Request
     *
     * @return List of {@link Movie}
     * @throws InternetConnectionException
     */
    private Movie startMovieRequest(String id) throws InternetConnectionException {
        if (isThereInternetConnection(mContext)) {
            try {
                return mWebServicesApiCallsImpl.getMovieDetail(id);
            } catch (RetrofitError e) {
                throw new InternetConnectionException(e.getMessage());
            }
        } else {
            throw new InternetConnectionException(mContext.getString(R.string.internet_connection_error));
        }
    }

    /**
     * Starts Movies List Request
     *
     * @return Search results
     * @throws InternetConnectionException
     */
    private SearchJSONResults startMovieFromListRequest(String query) throws InternetConnectionException {
        if (isThereInternetConnection(mContext)) {
            try {
                return mWebServicesApiCallsImpl.getMoviesList(query);
            } catch (RetrofitError e) {
                throw new InternetConnectionException(e.getMessage());
            }
        } else {
            throw new InternetConnectionException(mContext.getString(R.string.internet_connection_error));
        }
    }

    /**
     * Checks if there is Internet connection
     *
     * @param context {@link Context}
     * @return true or false
     */
    private boolean isThereInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
