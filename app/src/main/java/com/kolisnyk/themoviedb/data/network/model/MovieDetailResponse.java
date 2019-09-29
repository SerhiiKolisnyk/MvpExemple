package com.kolisnyk.themoviedb.data.network.model;

import com.google.gson.annotations.SerializedName;

public class MovieDetailResponse extends Movie {

    @SerializedName("adult")
    boolean isAdult;

    @SerializedName("status")
    String status;

    @SerializedName("revenue")
    String revenue;

    public MovieDetailResponse(int id, String title, String releaseDate, float rating, String thumbPath, String overview, String backdropPath, boolean isAdult, String status, String revenue) {
        super(id, title, releaseDate, rating, thumbPath, overview, backdropPath);
        this.isAdult = isAdult;
        this.status = status;
        this.revenue = revenue;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
