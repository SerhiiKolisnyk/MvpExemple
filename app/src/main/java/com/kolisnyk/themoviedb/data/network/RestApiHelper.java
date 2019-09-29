package com.kolisnyk.themoviedb.data.network;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;

import io.reactivex.Single;

public interface RestApiHelper {

    Single<MovieListResponse> getPopularMovies();

}
