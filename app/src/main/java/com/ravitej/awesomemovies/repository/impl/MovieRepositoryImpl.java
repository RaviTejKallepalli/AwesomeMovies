package com.ravitej.awesomemovies.repository.impl;

import com.ravitej.awesomemovies.datamapper.impl.MovieDTOConverter;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.network.NetworkUtils;
import com.ravitej.awesomemovies.repository.MovieRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MovieRepositoryImpl
    implements MovieRepository {

    private MovieDTOConverter movieDTOConverter;

    public MovieRepositoryImpl() {
        movieDTOConverter = new MovieDTOConverter();
    }

    @Override
    public Single<List<Movie>> fetchPopularMovies() {
        return NetworkUtils.getMovieAPI()
            .fetchPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(movieDTO -> Single.just(movieDTOConverter.convert(movieDTO)));
    }

    @Override
    public Single<List<Movie>> fetchTopRatedMovies() {
        return NetworkUtils.getMovieAPI()
            .fetchTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(movieDTO -> Single.just(movieDTOConverter.convert(movieDTO)));
    }
}
