package com.kolisnyk.themoviedb.ui.popular;

import com.kolisnyk.themoviedb.ui.base.MvpPresenter;


public interface PopularMvpPresenter <V extends PopularMvpView, I extends PopularMvpInteractor>
        extends MvpPresenter<V, I> {

    void onViewPrepared();
    void onDrawerDetailClick(int idOfFilm);

}
