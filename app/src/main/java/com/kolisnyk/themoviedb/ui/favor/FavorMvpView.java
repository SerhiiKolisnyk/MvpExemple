package com.kolisnyk.themoviedb.ui.favor;

import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.ui.base.MvpView;

import java.util.List;

public interface FavorMvpView extends MvpView {
    void showDetailFragment(int idOfFilm);

    void updateList(@Nullable List<MovieDetail> movies);

}
