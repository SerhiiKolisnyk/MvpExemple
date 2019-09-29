package com.kolisnyk.themoviedb.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kolisnyk.themoviedb.di.qualifier.ActivityContext;
import com.kolisnyk.themoviedb.di.scope.ActivityScope;
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

import java.util.ArrayList;

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
    PopularAdapter provideBlogAdapter() {
        return new PopularAdapter();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}

