package com.kolisnyk.themoviedb.ui.favor;

import com.kolisnyk.themoviedb.ui.base.MvpPresenter;

public interface FavorMvpPresenter <V extends FavorMvpView, I extends FavorMvpInteractor>
        extends MvpPresenter<V, I> {

    void onViewPrepared();
    void onDrawerDetailClick(int idOfFilm);
}
