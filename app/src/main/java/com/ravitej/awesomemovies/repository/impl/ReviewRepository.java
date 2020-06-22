package com.ravitej.awesomemovies.repository.impl;

import com.ravitej.awesomemovies.datamapper.impl.ReviewDTOConverter;
import com.ravitej.awesomemovies.domainmodel.Review;
import com.ravitej.awesomemovies.network.NetworkUtils;
import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import com.ravitej.awesomemovies.utils.impl.RxSchedularsProviderImpl;
import io.reactivex.Single;
import java.util.List;

public class ReviewRepository {

    private RxSchedularProvider rxSchedularProvider;
    private ReviewDTOConverter dtoConverter;

    public ReviewRepository() {
        this.rxSchedularProvider = RxSchedularsProviderImpl.getInstance();
        dtoConverter = new ReviewDTOConverter();
    }

    public Single<List<Review>> getReviews(int movieId) {
        return NetworkUtils.getMovieAPI()
            .fetchReviews(movieId)
            .subscribeOn(rxSchedularProvider.networkSchedular())
            .observeOn(rxSchedularProvider.uiSchedular())
            .flatMap(reviews -> Single.just(dtoConverter.convert(reviews)));
    }
}
