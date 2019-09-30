package com.kolisnyk.themoviedb.di.component;

import com.kolisnyk.themoviedb.di.module.ActivityModule;
import com.kolisnyk.themoviedb.di.scope.ActivityScope;
import com.kolisnyk.themoviedb.ui.detail.DetailFragment;
import com.kolisnyk.themoviedb.ui.favor.FavorFragment;
import com.kolisnyk.themoviedb.ui.main.MainActivity;
import com.kolisnyk.themoviedb.ui.popular.PopularFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(PopularFragment fragment);

    void inject(DetailFragment detailFragment);

    void inject(FavorFragment favorFragment);
}
