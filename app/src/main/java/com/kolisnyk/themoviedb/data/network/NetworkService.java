package com.kolisnyk.themoviedb.data.network;

import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService {
    public final static String BASE_URL="http://api.themoviedb.org/3/";
    public final static String API_KEY="5f496cfa82123e175c0cdd43b1ff0ac2";
    public final static String BASE_URL_IMG="https://image.tmdb.org/t/p/w200/";

    @GET("movie/popular")
    Single<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int PageNo);

    @GET("movie/{movie_id}")
    Single<MovieDetailResponse> getMovieDetailResponse(@Path("movie_id") String id, @Query("api_key") String apiKey);
}
