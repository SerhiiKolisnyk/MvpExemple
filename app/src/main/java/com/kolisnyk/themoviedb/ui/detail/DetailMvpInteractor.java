package com.kolisnyk.themoviedb.ui.detail;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.ui.base.MvpInteractor;

import java.util.List;

import io.reactivex.Single;

public interface DetailMvpInteractor extends MvpInteractor {
    Single<MovieDetail> getMovieDetailResponse(int idOfFilm);
    Single<MovieDetail> getMovieDetail(int idOfFilm);
    void addToFavor(MovieDetail movieDetail);
    void deleteFromFavor(int id);
}
