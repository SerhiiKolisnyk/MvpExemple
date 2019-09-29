package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.ui.base.BasePresenter;
import com.kolisnyk.themoviedb.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter <V extends MainMvpView, I extends MainMvpInteractor>
        extends BasePresenter<V, I> implements MainMvpPresenter<V, I> {
    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(I mvpInteractor,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

//
//        getCompositeDisposable().add(getInteractor().doLogoutApiCall()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<LogoutResponse>() {
//                    @Override
//                    public void accept(LogoutResponse response) throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getInteractor().setUserAsLoggedOut();
//                        getMvpView().hideLoading();
//                        getMvpView().openLoginActivity();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
//                        // handle the login error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));



    @Override
    public void onViewInitialized() {
//        getCompositeDisposable().add(getInteractor()
//                .getAllQuestions()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<List<Question>>() {
//                    @Override
//                    public void accept(List<Question> questionList) throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        if (questionList != null) {
//                            getMvpView().refreshQuestionnaire(questionList);
//                        }
//                    }
//                }));
    }



    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }
       //todo update hello world
    }

    @Override
    public void onDrawerGaleryClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showGalleryFragment();
    }
}
