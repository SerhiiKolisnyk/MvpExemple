package com.kolisnyk.themoviedb.ui.popular;

import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.data.network.model.Movie;
import com.kolisnyk.themoviedb.ui.base.MvpView;

import java.util.List;

public interface PopularMvpView extends MvpView {

    void updateList(@Nullable List<Movie> movies);

    void showDetailFragment(int idOfFilm);
}
