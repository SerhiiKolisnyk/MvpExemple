package com.kolisnyk.themoviedb.data.mapper;

import androidx.annotation.NonNull;

import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;

public final class Mapper {
    public static MovieDetail to( MovieDetailResponse mdr) {
        if (mdr==null)return null;
        return new MovieDetail(
                mdr.getId(),
                mdr.getTitle(),
                mdr.getReleaseDate(),
                mdr.getRating(),
                mdr.getThumbPath(),
                mdr.getOverview(),
                mdr.getBackdropPath(),
                mdr.isAdult(),
                mdr.getStatus(),
                mdr.getRevenue()
        );
    }
}
