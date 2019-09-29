package com.kolisnyk.themoviedb.di.component;

import android.app.Application;
import android.content.Context;

import com.kolisnyk.themoviedb.ThisApp;
import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.di.module.ApplicationModule;
import com.kolisnyk.themoviedb.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ThisApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

}
