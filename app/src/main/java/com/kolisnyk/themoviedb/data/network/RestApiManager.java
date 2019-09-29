package com.kolisnyk.themoviedb.data.network;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class RestApiManager implements RestApiHelper {
    private final String API_KEY="5f496cfa82123e175c0cdd43b1ff0ac2";

    NetworkService mService;

    @Inject
    public RestApiManager(NetworkService apiService) {
        mService = apiService;
    }
    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return mService.getPopularMovies(API_KEY,1);
    }
}
