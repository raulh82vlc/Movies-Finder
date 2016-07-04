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

package com.raulh82vlc.MoviesFinder.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie adapted for UI elements
 *
 * @author Raul Hernandez Lopez
 */
public class MovieFromListUI implements Parcelable {

    public static final Creator<MovieFromListUI> CREATOR = new Creator<MovieFromListUI>() {
        @Override
        public MovieFromListUI createFromParcel(Parcel in) {
            return new MovieFromListUI(in);
        }

        @Override
        public MovieFromListUI[] newArray(int size) {
            return new MovieFromListUI[size];
        }
    };
    private String title;
    private String year;
    private String url;
    private String imdbId;

    public MovieFromListUI() {
    }

    private MovieFromListUI(Parcel in) {
        title = in.readString();
        year = in.readString();
        url = in.readString();
        imdbId = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(url);
        dest.writeString(imdbId);
    }
}
