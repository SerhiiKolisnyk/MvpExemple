package com.kolisnyk.themoviedb.ui.detail;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.mapper.Mapper;
import com.kolisnyk.themoviedb.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Single;

public class DetailInteractor extends BaseInteractor implements  DetailMvpInteractor {
    @Inject
    DetailInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<MovieDetail> getMovieDetailResponse(int idOfFilm) {
        return getDataManager().getMovieDetailResponse(idOfFilm)
                .map(Mapper::to);
    }

    @Override
    public void addToFavor(MovieDetail movieDetail) {
        getDataManager().insert(movieDetail);
    }

    @Override
    public void deleteFromFavor(int id) {
        getDataManager().delete(id);
    }

    @Override
    public Single<MovieDetail> getMovieDetail(int idOfFilm) {
        return getDataManager().getByID(idOfFilm);
    }


}
