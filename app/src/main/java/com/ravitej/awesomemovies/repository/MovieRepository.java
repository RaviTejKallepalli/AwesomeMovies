package com.ravitej.awesomemovies.repository;

import com.ravitej.awesomemovies.domainmodel.Movie;
import io.reactivex.Single;
import java.util.List;

public interface MovieRepository {

    Single<List<Movie>> fetchPopularMovies();

    Single<List<Movie>> fetchTopRatedMovies();
}
