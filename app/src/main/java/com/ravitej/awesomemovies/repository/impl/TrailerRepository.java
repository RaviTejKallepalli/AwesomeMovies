package com.ravitej.awesomemovies.repository.impl;

import com.ravitej.awesomemovies.datamapper.impl.TrailerDTOConverter;
import com.ravitej.awesomemovies.domainmodel.Trailer;
import com.ravitej.awesomemovies.network.NetworkUtils;
import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import com.ravitej.awesomemovies.utils.impl.RxSchedularsProviderImpl;
import io.reactivex.Single;
import java.util.List;

public class TrailerRepository {

    private RxSchedularProvider rxSchedularProvider;
    private TrailerDTOConverter dtoConverter;

    public TrailerRepository() {
        this.rxSchedularProvider = RxSchedularsProviderImpl.getInstance();
        dtoConverter = new TrailerDTOConverter();
    }

    public Single<List<Trailer>> getTrailers(int movieId) {
        return NetworkUtils.getMovieAPI()
            .fetchTrailers(movieId)
            .subscribeOn(rxSchedularProvider.networkSchedular())
            .observeOn(rxSchedularProvider.uiSchedular())
            .flatMap(trailerDto -> Single.just(dtoConverter.convert(trailerDto)));
    }
}
