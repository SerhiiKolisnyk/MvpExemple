package com.kolisnyk.themoviedb.data.network;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {
    @GET("movie/popular")
    Single<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int PageNo);
}
