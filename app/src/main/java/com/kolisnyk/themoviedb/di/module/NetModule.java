package com.kolisnyk.themoviedb.di.module;

import com.kolisnyk.themoviedb.data.network.NetworkService;
import com.kolisnyk.themoviedb.data.network.RestApiHelper;
import com.kolisnyk.themoviedb.data.network.RestApiManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class NetModule {
    public NetModule() {
    }

    @Provides
    @Named("ApiKey")
    String provideApiKey() {
        return "5f496cfa82123e175c0cdd43b1ff0ac2";
    }

    @Provides
    @Singleton
    @Named("BaseUrl")
    String provideBaseUrl() {
        return "http://api.themoviedb.org/3/";
    }
    @Provides
    @Singleton
    RestApiHelper provideRestApiHelper(RestApiManager restApiManager) {
        return restApiManager;
    }
    @Provides
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Provides
    public NetworkService provideApiService() {
        return provideRetrofit("http://api.themoviedb.org/3/", provideClient()).create(NetworkService.class);
    }
}
