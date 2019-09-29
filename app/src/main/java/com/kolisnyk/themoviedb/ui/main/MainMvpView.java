package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.ui.base.MvpView;

import java.util.List;

public interface MainMvpView  extends MvpView {

    void showGalleryFragment();
    void showPopularFragmentFragment();


//    void showAboutFragment();

//
//    void showRateUsDialog();
//
//    void openMyFeedActivity();
//
    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
