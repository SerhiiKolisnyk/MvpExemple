package com.kolisnyk.themoviedb.ui.detail;

import androidx.annotation.NonNull;

import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DetailPresenter <V extends DetailMvpView, I extends DetailMvpInteractor>
        extends BasePresenter<V, I> implements DetailMvpPresenter<V, I>{

    @Inject
    public DetailPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(@NonNull int idOfFilm) {
        getCompositeDisposable().add(getInteractor()
                .getMovieDetailResponse(idOfFilm)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieDetailResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MovieDetailResponse movieDetailResponse)
                            throws Exception {
                        getMvpView().updateInfo(movieDetailResponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        // handle the error here
                        getMvpView().showMessage("No internet connection");
                        getMvpView().updateInfo(null);
                    }
                }));
    }
}
