package com.kolisnyk.themoviedb.data;

import android.content.Context;

import com.kolisnyk.themoviedb.data.db.DbManager;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.network.RestApiHelper;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.di.qualifier.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DataManagerImpl implements DataManager {
    private final Context mContext;
    private final RestApiHelper mApiHelper;
    private final DbManager mDbManager;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           RestApiHelper apiHelper,DbManager dbManager) {
        mContext = context;
        mApiHelper = apiHelper;
        mDbManager = dbManager;
    }

    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return mApiHelper.getPopularMovies();
    }

    @Override
    public Single<MovieDetailResponse> getMovieDetailResponse(int idOfFilm) {
        return mApiHelper.getMovieDetailResponse(idOfFilm);
    }

    @Override
    public Single<List<MovieDetail>> getFavorMovies() {
        return mDbManager.getFavorMovies();
    }
}
