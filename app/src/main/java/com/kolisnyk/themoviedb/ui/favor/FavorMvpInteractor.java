package com.kolisnyk.themoviedb.ui.favor;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.ui.base.MvpInteractor;

import java.util.List;

import io.reactivex.Single;

public interface FavorMvpInteractor extends MvpInteractor {
    Single<List<MovieDetail>> getFavorMovies();

}
