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

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raulh82vlc.MoviesFinder.MoviesFinderApp;
import com.raulh82vlc.MoviesFinder.R;
import com.raulh82vlc.MoviesFinder.di.components.MovieDetailsComponent;
import com.raulh82vlc.MoviesFinder.di.modules.ActivityModule;
import com.raulh82vlc.MoviesFinder.domain.exceptions.InternetConnectionException;
import com.raulh82vlc.MoviesFinder.domain.models.MovieFromListUI;
import com.raulh82vlc.MoviesFinder.di.components.DaggerMovieDetailsComponent;
import com.raulh82vlc.MoviesFinder.domain.models.MovieUI;
import com.raulh82vlc.MoviesFinder.ui.presentation.MovieDetailsPresenter;
import com.raulh82vlc.MoviesFinder.ui.utils.ImageRendering;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * <p>Movie Details</p>
 *
 * @author Raul Hernandez Lopez
 */
public class MovieDetailsActivity extends BaseActivity implements MovieDetailsPresenter.View {
    // UI Injections
    @InjectView(R.id.title_txt)
    TextView mTitleTxt;
    @InjectView(R.id.year_txt)
    TextView mYearTxt;
    @InjectView(R.id.synopsis_txt)
    TextView mSynopsisTxt;
    @InjectView(R.id.rating_txt)
    TextView mRatingTxt;
    @InjectView(R.id.vote_txt)
    TextView mVotesTxt;
    @InjectView(R.id.actors_txt)
    TextView mActorsTxt;
    @InjectView(R.id.pg_txt)
    TextView mPGTxt;
    @InjectView(R.id.awards_txt)
    TextView mAwardsTxt;
    @InjectView(R.id.duration_txt)
    TextView mDurationTxt;
    @InjectView(R.id.director_txt)
    TextView mDirectorTxt;
    @InjectView(R.id.director_label_txt)
    TextView mDirectorLabelLink;
    @InjectView(R.id.main_image_view)
    ImageView mMainImageView;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    // DI
    @Inject
    ImageRendering imageRendering;
    @Inject
    MovieDetailsPresenter presenter;

    // CONSTANTS
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    protected final static String KEY_MOVIE = "MovieImage";
    private final static String IMG_TRANSITION_TAG = "activitycompat_transition_by_img";

    // Data structures
    private MovieFromListUI mMovie;

    public static void navigateToDetailsActivity(AppCompatActivity activity, MovieFromListUI movieFromListUI, View view) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.KEY_MOVIE, movieFromListUI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, IMG_TRANSITION_TAG);
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override
    public boolean onNavigateUp () {
        onBackPressed();
        return true;
    }

    // There is not need for a component since there are not injections, but easily could be extended with Interactor of our domain
    private MovieDetailsComponent movieDetailsComponent;
    public MovieDetailsComponent component() {
        if (movieDetailsComponent == null) {
            movieDetailsComponent = DaggerMovieDetailsComponent.builder()
                    .applicationComponent(((MoviesFinderApp) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return movieDetailsComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.postponeEnterTransition(this);
        }
        component().inject(this);
        ButterKnife.inject(this);
        presenter.setView(this);
        if (getIntent() != null) {
            mMovie = getIntent().getParcelableExtra(KEY_MOVIE);
            setInitialTitle();
            setInfo();
            setImage();
            setMaterialEffect();
            getMoreDetails();
        }
        setToolbarInitialisation();
    }

    private void getMoreDetails() {
        try {
            presenter.start(mMovie.getImdbId());
        } catch (InternetConnectionException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void setImage() {
        imageRendering.loadImage(mMovie.getUrl(), mMainImageView);
    }

    private void setInfo() {
        mTitleTxt.setText(mMovie.getTitle());
        String template = getString(R.string.format_year_sketch);
        mYearTxt.setText(String.format(template, mMovie.getYear()));
    }

    /**
     * <p>Sets transition and from which view
     * postpone the start until is indicated</p>
     *
     **/
    private void setMaterialEffect() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(mMainImageView, IMG_TRANSITION_TAG);
            ActivityCompat.startPostponedEnterTransition(this);
        }
    }

    @Override
    protected void setInitialTitle() {
        mToolbar.setTitle(mMovie.getTitle());
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
        mCollapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        mCollapsingToolbar.setTitle(mMovie.getTitle());
    }

    @Override
    protected void setToolbarInitialisation() {
        //mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }

    @Override
    public void loadMovieDetails(MovieUI movie) {
        setDetails(movie);
        setClickListener();

    }

    private void setClickListener() {
        mDirectorLabelLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.link)));
                startActivity(i);
            }
        });
    }

    private void setDetails(MovieUI movie) {
        mSynopsisTxt.setText(movie.getSynopsis());
        mRatingTxt.setText(movie.getRating());
        String template = getString(R.string.format_votes_sketch);
        mVotesTxt.setText(String.format(template, movie.getVotes()));
        mActorsTxt.setText(movie.getActors());
        mPGTxt.setText(movie.getRatedForQualifiedPublic());
        mAwardsTxt.setText(movie.getAwards());
        mDurationTxt.setText(movie.getDuration());
        mDirectorTxt.setText(movie.getDirector());
    }

    @Override
    public void errorGettingMovieDetails(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
