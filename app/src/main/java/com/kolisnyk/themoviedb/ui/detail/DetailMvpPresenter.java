package com.kolisnyk.themoviedb.ui.detail;

import androidx.annotation.NonNull;

import com.kolisnyk.themoviedb.ui.base.MvpPresenter;

public interface DetailMvpPresenter<V extends DetailMvpView, I extends DetailMvpInteractor>
        extends MvpPresenter<V, I> {

    void onViewPrepared(@NonNull int idOfFilm);

}
