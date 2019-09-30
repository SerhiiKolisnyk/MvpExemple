package com.kolisnyk.themoviedb.ui.favor;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.ui.base.BaseInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FavorInteractor extends BaseInteractor implements FavorMvpInteractor {
    @Inject
    public FavorInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<List<MovieDetail>> getFavorMovies() {
        return getDataManager().getFavorMovies();
    }
}
