package com.kolisnyk.themoviedb.di.module;

import android.app.Application;
import android.content.Context;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.data.DataManagerImpl;
import com.kolisnyk.themoviedb.data.db.DbHelper;
import com.kolisnyk.themoviedb.data.db.DbManager;
import com.kolisnyk.themoviedb.data.network.NetworkService;
import com.kolisnyk.themoviedb.data.network.RestApiHelper;
import com.kolisnyk.themoviedb.data.network.RestApiManager;
import com.kolisnyk.themoviedb.di.qualifier.ApiInfo;
import com.kolisnyk.themoviedb.di.qualifier.ApplicationContext;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
