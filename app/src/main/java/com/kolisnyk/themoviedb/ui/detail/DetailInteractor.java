package com.kolisnyk.themoviedb.ui.detail;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Single;

public class DetailInteractor extends BaseInteractor implements  DetailMvpInteractor {
    @Inject
    public DetailInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<MovieDetailResponse> getMovieDetailResponse(int idOfFilm) {
        return getDataManager().getMovieDetailResponse(idOfFilm);
    }
}
