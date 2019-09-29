package com.kolisnyk.themoviedb.ui.popular;

import android.util.Log;

import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class PopularPresenter<V extends PopularMvpView, I extends PopularMvpInteractor>
        extends BasePresenter<V, I> implements PopularMvpPresenter<V, I> {
    private static final String TAG="PopularPresenter";
    @Inject
    public PopularPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getCompositeDisposable().add(getInteractor()
                .getPopularMovies()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieListResponse>() {
                    @Override
                    public void accept(@NonNull MovieListResponse movieListResponse)
                            throws Exception {
                        Log.d(TAG, "accept: movieListResponse"+movieListResponse);
                        if (movieListResponse != null) {
                            getMvpView().updateList(movieListResponse);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        // handle the error here
                        getMvpView().showMessage("ERROR:"+throwable.getMessage());
                    }
                }));
    }
}
