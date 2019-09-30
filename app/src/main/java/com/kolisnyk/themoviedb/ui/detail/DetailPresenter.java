package com.kolisnyk.themoviedb.ui.detail;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.mapper.Mapper;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DetailPresenter<V extends DetailMvpView, I extends DetailMvpInteractor>
        extends BasePresenter<V, I> implements DetailMvpPresenter<V, I> {
    private final static String TAG="DetailPresenter";
    @Nullable
    private MovieDetail movieDetail;

    @Inject
    DetailPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared( int idOfFilm) {
        getCompositeDisposable().add(getInteractor()
                .getMovieDetail(idOfFilm)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieDetail -> {
                            movieDetail.setFavor(true);
                            getMvpView().updateInfo(movieDetail);
                            this.movieDetail = movieDetail;
                        }
                        ,
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            // yes it bad idea, but ...
                            onViewPreparedFromNet(idOfFilm);
                        }));
    }
    private void onViewPreparedFromNet(@NonNull int idOfFilm) {
        getCompositeDisposable().add(getInteractor()
                .getMovieDetailResponse(idOfFilm)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieDetail -> {
                            movieDetail.setFavor(false);
                            getMvpView().updateInfo(movieDetail);
                            this.movieDetail = movieDetail;
                        }
                        ,
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            // handle the error here
                            getMvpView().showMessage("No internet connection");
                            getMvpView().updateInfo(null);
                        }));
    }

    @Override
    public void onFavorClick() {
        if (movieDetail != null) {
            if (movieDetail.isFavor()){
                getInteractor().deleteFromFavor(movieDetail.getId());
                getMvpView().onFavorDraw(false);
                movieDetail.setFavor(false);
            }else {
                getInteractor().addToFavor(movieDetail);
                getMvpView().onFavorDraw(true);
                movieDetail.setFavor(true);
            }
        }
//        if (movieDetailResponse != null) Mapper.to(movieDetail);
    }
}
