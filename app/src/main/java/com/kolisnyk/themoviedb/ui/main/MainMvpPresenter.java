package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.di.scope.ActivityScope;
import com.kolisnyk.themoviedb.ui.base.MvpPresenter;

@ActivityScope
public interface MainMvpPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends MvpPresenter<V, I> {

    void onDrawerGaleryClick();

    void onViewInitialized();

    void onNavMenuCreated();
}
