package com.ravitej.awesomemovies.details;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ravitej.awesomemovies.dao.MovieDao;
import com.ravitej.awesomemovies.database.MovieDatabase;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.domainmodel.Review;
import com.ravitej.awesomemovies.domainmodel.Trailer;
import com.ravitej.awesomemovies.repository.impl.ReviewRepository;
import com.ravitej.awesomemovies.repository.impl.TrailerRepository;
import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import com.ravitej.awesomemovies.utils.impl.RxSchedularsProviderImpl;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CheckResult")
public class DetailsViewModel extends ViewModel {

    private static final String TAG = DetailsViewModel.class.getSimpleName();
    private Movie movie;
    private RxSchedularProvider rxSchedularProvider;
    private MovieDatabase movieDatabase;
    private MovieDao movieDao;
    private MutableLiveData<Boolean> favoriteLiveData;
    private MutableLiveData<List<Trailer>> trailersLiveData;
    private MutableLiveData<List<Review>> reviewsLiveData;
    private TrailerRepository trailerRepository;
    private ReviewRepository reviewRepository;

    public DetailsViewModel(Application application, Movie movie) {
        rxSchedularProvider = RxSchedularsProviderImpl.getInstance();
        favoriteLiveData = new MutableLiveData<>(false);
        movieDatabase = MovieDatabase.getsInstance(application);
        movieDao = movieDatabase.movieDao();
        trailerRepository = new TrailerRepository();
        trailersLiveData = new MutableLiveData<>(new ArrayList<>());
        reviewsLiveData = new MutableLiveData<>(new ArrayList<>());
        reviewRepository = new ReviewRepository();
        Log.e(TAG, "Details ViewModel created");
        this.movie = movie;
        init(movie);
    }

    //FIXME: Should be moved to constructor?
    public void init(Movie movie) {
        getMovie(movie.getMovieId());
        getTrailers(movie.getMovieId());
        getReviews(movie.getMovieId());
    }


    public void getReviews(int movieId) {
        reviewRepository.getReviews(movieId)
            .subscribe(
                list -> reviewsLiveData.setValue(list),
                throwable -> Log.e(TAG, throwable.toString())
            );
    }

    public void getTrailers(int movieId) {
        trailerRepository.getTrailers(movieId)
            .subscribe(
                list -> trailersLiveData.setValue(list),
                throwable -> Log.e(TAG, throwable.toString())
            );
    }

    public LiveData<List<Trailer>> getTrailersLiveData() {
        return trailersLiveData;
    }

    public LiveData<Boolean> getFavoriteLiveData() {
        return favoriteLiveData;
    }

    public LiveData<List<Review>> getReviewsLiveData() {
        return reviewsLiveData;
    }

    public void deleteFromFavorite() {
        movie.setFavorite(false);
        movieDao.deleteMovie(movie.getMovieId())
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular())
            .subscribe(() -> favoriteLiveData.setValue(movie.isFavorite()));
    }

    public void addMovieToFavorite() {
        movie.setFavorite(true);
        movieDao.insertFavoriteMovie(movie)
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular())
            .subscribe(() -> favoriteLiveData.setValue(movie.isFavorite()));
    }

    public void getMovie(int movieId) {
        movieDao.fetchMovie(movieId)
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular())
            .subscribe(movie -> favoriteLiveData.setValue(movie.isFavorite()));
    }
}
