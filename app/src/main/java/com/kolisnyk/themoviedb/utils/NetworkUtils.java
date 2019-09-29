package com.kolisnyk.themoviedb.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.HttpException;

public class NetworkUtils {

    public static boolean isInternetConnected(Context appContext) {
        ConnectivityManager conMgr = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public static String handleApiError(Throwable error) {
        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    return "Unauthorised User ";
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    return "Forbidden";
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    return"Internal Server Error";
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    return"Bad Request";
                default:
                   return error.getLocalizedMessage();

            }
        }
        return error.getLocalizedMessage();
    }
}
