package com.kolisnyk.themoviedb.ui.base;

import com.kolisnyk.themoviedb.data.DataManager;

import javax.inject.Inject;

public class BaseInteractor implements MvpInteractor {
    private DataManager dataManager;

    @Inject
    public BaseInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public DataManager getDataManager() {
        return dataManager;
    }


}
