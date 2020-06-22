package com.ravitej.awesomemovies.details;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.ravitej.awesomemovies.domainmodel.Movie;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private Movie movie;

    public DetailsViewModelFactory(Application application, Movie movie) {
        this.application = application;
        this.movie = movie;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsViewModel(application, movie);
    }
}
