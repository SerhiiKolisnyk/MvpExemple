package com.kolisnyk.themoviedb.ui.base;
@SuppressWarnings("unused")
public interface MvpPresenter<V extends MvpView, I extends MvpInteractor> {
    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Throwable error);

    V getMvpView();

    I getInteractor();

    boolean isViewAttached();

    void checkViewAttached() throws BasePresenter.MvpViewNotAttachedException;

}
