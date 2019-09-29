package com.kolisnyk.themoviedb.ui.detail;

import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.ui.base.MvpInteractor;

import io.reactivex.Single;

public interface DetailMvpInteractor extends MvpInteractor {
    Single<MovieDetailResponse> getMovieDetailResponse(int idOfFilm);
}
