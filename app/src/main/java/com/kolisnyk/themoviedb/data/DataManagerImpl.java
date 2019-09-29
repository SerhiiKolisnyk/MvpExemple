package com.kolisnyk.themoviedb.data;

import android.content.Context;

import com.kolisnyk.themoviedb.data.network.RestApiHelper;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.di.qualifier.ApplicationContext;

import javax.inject.Inject;

import io.reactivex.Single;

public class DataManagerImpl implements DataManager {
    private final Context mContext;
    private final RestApiHelper mApiHelper;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           RestApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return mApiHelper.getPopularMovies();
    }

    @Override
    public Single<MovieDetailResponse> getMovieDetailResponse(int idOfFilm) {
        return mApiHelper.getMovieDetailResponse(idOfFilm);
    }
}
