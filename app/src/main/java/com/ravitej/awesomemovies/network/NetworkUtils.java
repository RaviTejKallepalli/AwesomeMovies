package com.ravitej.awesomemovies.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ravitej.awesomemovies.BuildConfig;
import com.ravitej.awesomemovies.apis.MovieAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342/";
    public static final String API_KEY = BuildConfig.API_KEY;

    private Retrofit retrofit;
    private static NetworkUtils instance;
    private static MovieAPI movieAPI;

    private NetworkUtils() {
    }

    public static NetworkUtils getInstance() {
        if (instance == null) {
            instance = new NetworkUtils();
        }

        return instance;
    }

    private Retrofit buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createOkHttpClient())
                .baseUrl(BASE_MOVIE_URL)
                .build();
        }

        return retrofit;
    }

    private Gson createGson() {
        return new GsonBuilder().create();
    }

    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
            .addNetworkInterceptor(getHttpLoggingInterceptor())
            .build();
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
            .setLevel(Level.BODY);
    }

    public static MovieAPI getMovieAPI() {
        if (movieAPI == null) {
            movieAPI = getInstance().buildRetrofit().create(MovieAPI.class);
        }

        return movieAPI;
    }
}
