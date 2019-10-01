package com.kolisnyk.themoviedb.ui.popular;


import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.NetworkUtils;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PopularPresenter<V extends PopularMvpView, I extends PopularMvpInteractor>
        extends BasePresenter<V, I> implements PopularMvpPresenter<V, I> {
    private static final String TAG="PopularPresenter";
    @Inject
    public PopularPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        if (!getMvpView().isNetworkConnected()){
            getMvpView().showMessage("No internet connection");
            getMvpView().updateList(null);
            return;
        }
        getCompositeDisposable().add(getInteractor()
                .getPopularMovies()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieListResponse -> {
                    if (movieListResponse != null) {
                        getMvpView().updateList(movieListResponse.getResults());
                    }

                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    // handle the error here
                    getMvpView().showMessage(NetworkUtils.handleApiError(throwable));
                }));
    }

    @Override
    public void onDrawerDetailClick(int idOfFilm) {
        getMvpView().showDetailFragment(idOfFilm);
    }
}
