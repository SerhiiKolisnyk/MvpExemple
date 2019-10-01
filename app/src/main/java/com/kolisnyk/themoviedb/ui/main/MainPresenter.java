package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter <V extends MainMvpView, I extends MainMvpInteractor>
        extends BasePresenter<V, I> implements MainMvpPresenter<V, I> {
    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(I mvpInteractor,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }





    @Override
    public void onViewInitialized() {
    }



    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }
    }

    @Override
    public void onDrawerGaleryClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showGalleryFragment();
    }
}
