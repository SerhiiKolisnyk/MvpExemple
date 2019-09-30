package com.kolisnyk.themoviedb.data;

import com.kolisnyk.themoviedb.data.db.DbHelper;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.network.RestApiHelper;

import java.util.List;

import io.reactivex.Single;

public interface DataManager extends RestApiHelper, DbHelper {
}
