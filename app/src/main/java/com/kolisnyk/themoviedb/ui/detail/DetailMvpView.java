package com.kolisnyk.themoviedb.ui.detail;

import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void updateInfo(@Nullable MovieDetail movieDetailResponse);

    void onFavorDraw(boolean b);
}
