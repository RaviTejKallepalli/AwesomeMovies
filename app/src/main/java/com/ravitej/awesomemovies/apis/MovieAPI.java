package com.ravitej.awesomemovies.apis;

import com.ravitej.awesomemovies.dtos.MovieDTO;
import com.ravitej.awesomemovies.dtos.ReviewListDTO;
import com.ravitej.awesomemovies.dtos.TrailerWrapperDTO;
import com.ravitej.awesomemovies.network.NetworkUtils;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieAPI {

    @GET("movie/popular?api_key=" + NetworkUtils.API_KEY)
    Single<MovieDTO> fetchPopularMovies();

    @GET("movie/top_rated?api_key=" + NetworkUtils.API_KEY)
    Single<MovieDTO> fetchTopRatedMovies();

    @GET("movie/{id}/videos?api_key=" + NetworkUtils.API_KEY)
    Single<TrailerWrapperDTO> fetchTrailers(@Path("id") int movieId);

    @GET("movie/{id}/reviews?api_key=" + NetworkUtils.API_KEY)
    Single<ReviewListDTO> fetchReviews(@Path("id") int movieId);
}
