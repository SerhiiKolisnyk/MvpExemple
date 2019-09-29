package com.kolisnyk.themoviedb.ui.popular;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.ui.base.MvpInteractor;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface PopularMvpInteractor extends MvpInteractor {
    Single<MovieListResponse> getPopularMovies();

}
