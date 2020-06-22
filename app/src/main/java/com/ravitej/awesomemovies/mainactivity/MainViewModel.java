package com.ravitej.awesomemovies.mainactivity;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.repository.MovieRepository;
import com.ravitej.awesomemovies.repository.impl.MovieRepositoryImpl;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private MovieRepository movieRepository;
    private MutableLiveData<List<Movie>> movieListLiveData;

    public MainViewModel(Application application) {
        movieRepository = new MovieRepositoryImpl(application);
        movieListLiveData = new MutableLiveData<>(new ArrayList<>());
    }

    @SuppressWarnings("CheckResult")
    public void fetchPopularMovies() {
        Disposable disposable = movieRepository.fetchPopularMovies()
            .subscribe(
                movieDTO -> movieListLiveData.setValue(movieDTO),
                throwable -> Log.e(TAG, "Error" + throwable.getMessage())
            );
    }

    @SuppressWarnings("CheckResult")
    public void fetchTopRatedMovies() {
        Disposable disposable = movieRepository.fetchTopRatedMovies()
            .subscribe(
                movieDTO -> movieListLiveData.setValue(movieDTO),
                throwable -> Log.e(TAG, "Error" + throwable.getMessage())
            );
    }

    @SuppressWarnings("CheckResult")
    public void fetchFavoriteMovies() {
        Disposable disposable = movieRepository.fetchFavoriteMovies()
            .subscribe(
                movieDTO -> movieListLiveData.setValue(movieDTO),
                throwable -> Log.e(TAG, "Error" + throwable.getMessage())
            );
    }

    public LiveData<List<Movie>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public void init() {
        fetchPopularMovies();
    }
}
