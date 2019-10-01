package com.kolisnyk.themoviedb.data.network;

import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static com.kolisnyk.themoviedb.data.network.NetworkService.API_KEY;

@Singleton
public class RestApiManager implements RestApiHelper {

    NetworkService mService;

    @Inject
    public RestApiManager(NetworkService apiService) {
        mService = apiService;
    }
    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return mService.getPopularMovies(API_KEY,1);
    }

    @Override
    public Single<MovieDetailResponse> getMovieDetailResponse(int idOfFilm) {
        return mService.getMovieDetailResponse(String.valueOf(idOfFilm),API_KEY);
    }
}
