package com.kolisnyk.themoviedb.ui.base;


public interface MvpView {

    void showMessage(String message);

    boolean isNetworkConnected();

}
