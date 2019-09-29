package com.kolisnyk.themoviedb.ui.detail;

import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void updateInfo(@Nullable MovieDetailResponse movieDetailResponse);
}
