package com.ravitej.awesomemovies.apis;

import com.ravitej.awesomemovies.dtos.MovieDTO;
import com.ravitej.awesomemovies.network.NetworkUtils;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface MovieAPI {

    @GET("movie/popular?api_key=" + NetworkUtils.API_KEY)
    Single<MovieDTO> fetchPopularMovies();

    @GET("movie/top_rated?api_key=" + NetworkUtils.API_KEY)
    Single<MovieDTO> fetchTopRatedMovies();
}
