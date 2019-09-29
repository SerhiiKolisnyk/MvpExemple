package com.kolisnyk.themoviedb.ui.popular;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.ui.base.MvpView;

public interface PopularMvpView extends MvpView {

    void updateList(MovieListResponse movieListResponse);
}
