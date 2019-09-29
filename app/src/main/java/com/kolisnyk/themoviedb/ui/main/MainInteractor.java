package com.kolisnyk.themoviedb.ui.main;

import com.kolisnyk.themoviedb.data.DataManager;
import com.kolisnyk.themoviedb.ui.base.BaseInteractor;

import javax.inject.Inject;

public class MainInteractor extends BaseInteractor
        implements MainMvpInteractor{
    @Inject
    public MainInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
