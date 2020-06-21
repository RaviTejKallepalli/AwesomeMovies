package com.ravitej.awesomemovies.repository.impl;

import android.app.Application;
import com.ravitej.awesomemovies.dao.MovieDao;
import com.ravitej.awesomemovies.database.MovieDatabase;
import com.ravitej.awesomemovies.datamapper.impl.MovieDTOConverter;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.network.NetworkUtils;
import com.ravitej.awesomemovies.repository.MovieRepository;
import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import com.ravitej.awesomemovies.utils.impl.RxSchedularsProviderImpl;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

//TODO: Which class is responsible to identify the network connection and get the stale/stored data?
public class MovieRepositoryImpl
    implements MovieRepository {

    private MovieDTOConverter movieDTOConverter;
    private MovieDatabase movieDatabase;
    private MovieDao movieDao;
    private RxSchedularProvider rxSchedularProvider;

    public MovieRepositoryImpl(Application application) {
        this.movieDTOConverter = new MovieDTOConverter();
        this.movieDatabase = MovieDatabase.getsInstance(application);
        this.movieDao = movieDatabase.movieDao();
        this.rxSchedularProvider = RxSchedularsProviderImpl.getInstance();
    }

    @Override
    public Single<List<Movie>> fetchPopularMovies() {
        return NetworkUtils.getMovieAPI()
            .fetchPopularMovies()
            .subscribeOn(rxSchedularProvider.networkSchedular())
            .observeOn(rxSchedularProvider.uiSchedular())
            .flatMap(movieDTO -> Single.just(movieDTOConverter.convert(movieDTO)));
    }

    @Override
    public Single<List<Movie>> fetchTopRatedMovies() {
        return NetworkUtils.getMovieAPI()
            .fetchTopRatedMovies()
            .subscribeOn(rxSchedularProvider.networkSchedular())
            .observeOn(rxSchedularProvider.uiSchedular())
            .flatMap(movieDTO -> Single.just(movieDTOConverter.convert(movieDTO)));
    }

    //TODO: Should i return LiveData instead of Observable?
    @Override
    public Observable<List<Movie>> fetchFavoriteMovies() {
        return movieDao.getAllFavoriteMovies()
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular());
    }
}
