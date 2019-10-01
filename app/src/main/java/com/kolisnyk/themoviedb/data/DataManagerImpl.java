package com.kolisnyk.themoviedb.data;

import android.content.Context;

import com.kolisnyk.themoviedb.data.db.DbHelper;
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
    private final DbHelper mDbHelper;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           RestApiHelper apiHelper,DbHelper dbManager) {
        mContext = context;
        mApiHelper = apiHelper;
        mDbHelper = dbManager;
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
        return mDbHelper.getFavorMovies();
    }

    @Override
    public void insert(MovieDetail movieDetail) {
        mDbHelper.insert(movieDetail);
    }

    @Override
    public void delete(int id) {
        mDbHelper.delete(id);
    }

    @Override
    public Single<MovieDetail> getByID(int id) {
        return mDbHelper.getByID(id);
    }
}
