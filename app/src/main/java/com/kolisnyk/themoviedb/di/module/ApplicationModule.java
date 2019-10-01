package com.kolisnyk.themoviedb.di.module;

import android.app.Application;
import android.content.Context;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.DataManagerImpl;
import com.kolisnyk.themoviedb.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl mDataManager) {
        return mDataManager;
    }


}
