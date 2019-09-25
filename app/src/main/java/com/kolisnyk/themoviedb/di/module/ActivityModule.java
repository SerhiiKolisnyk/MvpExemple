package com.kolisnyk.themoviedb.di.module;

import androidx.appcompat.app.AppCompatActivity;

import dagger.Module;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }
}

