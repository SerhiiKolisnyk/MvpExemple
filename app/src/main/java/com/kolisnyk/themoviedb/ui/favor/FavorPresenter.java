package com.kolisnyk.themoviedb.ui.favor;


import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FavorPresenter<V extends FavorMvpView, I extends FavorMvpInteractor>
        extends BasePresenter<V, I> implements FavorMvpPresenter<V, I> {
    private final static String TAG = "FavorPresenter";

    @Inject
    public FavorPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getCompositeDisposable().add(getInteractor()
                .getFavorMovies()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieDetails -> {
                    if (movieDetails != null) {
                        getMvpView().updateList(movieDetails);
                    }


                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().updateList(null);
                    // handle the error here
                }));
    }

    @Override
    public void onDrawerDetailClick(int idOfFilm) {
        getMvpView().showDetailFragment(idOfFilm);
    }
}
