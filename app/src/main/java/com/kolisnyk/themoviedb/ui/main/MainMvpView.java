package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showGalleryFragment();

    void showPopularFragmentFragment();


    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
