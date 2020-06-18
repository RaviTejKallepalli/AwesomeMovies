package com.ravitej.awesomemovies.apis;

import com.ravitej.awesomemovies.dtos.MovieDTO;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface MovieAPI {

    @GET("movie/popular?api_key=3ddb3bad87271ff98bac18734a2591f6")
    Single<MovieDTO> fetchPopularMovies();

    @GET("movie/top_rated?api_key=3ddb3bad87271ff98bac18734a2591f6")
    Single<MovieDTO> fetchTopRatedMovies();
}
