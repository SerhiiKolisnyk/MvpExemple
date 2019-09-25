package com.kolisnyk.themoviedb.di.component;

import com.kolisnyk.themoviedb.di.module.ActivityModule;
import com.kolisnyk.themoviedb.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
