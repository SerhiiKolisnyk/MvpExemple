package com.kolisnyk.themoviedb.ui.popular;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Single;

public class PopularInteractor extends BaseInteractor implements PopularMvpInteractor {

    @Inject
    public PopularInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return getDataManager().getPopularMovies();
    }
}
