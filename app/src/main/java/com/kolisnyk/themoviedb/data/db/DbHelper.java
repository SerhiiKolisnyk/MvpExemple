package com.kolisnyk.themoviedb.data.db;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;

import java.util.List;

import io.reactivex.Single;

public interface DbHelper {
    Single<List<MovieDetail>> getFavorMovies();
}
