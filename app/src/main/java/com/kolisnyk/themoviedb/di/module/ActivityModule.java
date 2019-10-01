package com.kolisnyk.themoviedb.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kolisnyk.themoviedb.di.qualifier.ActivityContext;
import com.kolisnyk.themoviedb.di.scope.ActivityScope;
import com.kolisnyk.themoviedb.ui.detail.DetailInteractor;
import com.kolisnyk.themoviedb.ui.detail.DetailMvpInteractor;
import com.kolisnyk.themoviedb.ui.detail.DetailMvpPresenter;
import com.kolisnyk.themoviedb.ui.detail.DetailMvpView;
import com.kolisnyk.themoviedb.ui.detail.DetailPresenter;
import com.kolisnyk.themoviedb.ui.favor.FavorAdapter;
import com.kolisnyk.themoviedb.ui.favor.FavorInteractor;
import com.kolisnyk.themoviedb.ui.favor.FavorMvpInteractor;
import com.kolisnyk.themoviedb.ui.favor.FavorMvpPresenter;
import com.kolisnyk.themoviedb.ui.favor.FavorMvpView;
import com.kolisnyk.themoviedb.ui.favor.FavorPresenter;
import com.kolisnyk.themoviedb.ui.main.MainInteractor;
import com.kolisnyk.themoviedb.ui.main.MainMvpInteractor;
import com.kolisnyk.themoviedb.ui.main.MainMvpPresenter;
import com.kolisnyk.themoviedb.ui.main.MainMvpView;
import com.kolisnyk.themoviedb.ui.main.MainPresenter;
import com.kolisnyk.themoviedb.ui.popular.PopularAdapter;
import com.kolisnyk.themoviedb.ui.popular.PopularInteractor;
import com.kolisnyk.themoviedb.ui.popular.PopularMvpInteractor;
import com.kolisnyk.themoviedb.ui.popular.PopularMvpPresenter;
import com.kolisnyk.themoviedb.ui.popular.PopularMvpView;
import com.kolisnyk.themoviedb.ui.popular.PopularPresenter;
import com.kolisnyk.themoviedb.utils.rx.AppSchedulerProvider;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    MainMvpPresenter<MainMvpView, MainMvpInteractor> provideMainPresenter(
            MainPresenter<MainMvpView, MainMvpInteractor> presenter) {
        return presenter;
    }
    @Provides
    @ActivityScope
    MainMvpInteractor provideMainMvpInteractor(MainInteractor interactor) {
        return interactor;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    PopularMvpPresenter<PopularMvpView, PopularMvpInteractor> providePopularMvpPresenter(
            PopularPresenter<PopularMvpView, PopularMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    PopularMvpInteractor providePopularMvpInteractor(PopularInteractor interactor) {
        return interactor;
    }

    @Provides
    PopularAdapter providePopularAdapter() {
        return new PopularAdapter();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
    @Provides
    @ActivityScope
    DetailMvpInteractor provideDetailMvpInteractor(DetailInteractor interactor) {
        return interactor;
    }
    @Provides
    DetailMvpPresenter<DetailMvpView, DetailMvpInteractor> provideDetailMvpPresenter(
            DetailPresenter<DetailMvpView, DetailMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    FavorMvpInteractor provideFavorMvpInteractor(FavorInteractor interactor) {
        return interactor;
    }
    @Provides
    FavorMvpPresenter<FavorMvpView, FavorMvpInteractor> provideFavorMvpPresenter(
            FavorPresenter<FavorMvpView, FavorMvpInteractor> presenter) {
        return presenter;
    }


    @Provides
    FavorAdapter provideFavorAdapter() {
        return new FavorAdapter();
    }

}

