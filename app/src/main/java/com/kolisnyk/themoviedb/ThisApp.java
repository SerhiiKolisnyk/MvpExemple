package com.kolisnyk.themoviedb;

import android.app.Application;

import com.kolisnyk.themoviedb.di.component.ApplicationComponent;
import com.kolisnyk.themoviedb.di.component.DaggerApplicationComponent;
import com.kolisnyk.themoviedb.di.module.ApplicationModule;

public class ThisApp extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


//        AndroidNetworking.initialize(getApplicationContext());
//        if (BuildConfig.DEBUG) {
//            AndroidNetworking.enableLogging(Level.BODY);
//        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
