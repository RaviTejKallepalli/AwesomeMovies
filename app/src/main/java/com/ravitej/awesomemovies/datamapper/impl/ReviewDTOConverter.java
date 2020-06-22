package com.ravitej.awesomemovies.datamapper.impl;

import android.util.Log;
import com.ravitej.awesomemovies.datamapper.DataMapper;
import com.ravitej.awesomemovies.domainmodel.Review;
import com.ravitej.awesomemovies.domainmodel.Review.Builder;
import com.ravitej.awesomemovies.dtos.ReviewListDTO;
import com.ravitej.awesomemovies.dtos.ReviewListDTO.ReviewDTO;
import java.util.ArrayList;
import java.util.List;

public class ReviewDTOConverter implements DataMapper<ReviewListDTO, List<Review>> {

    private static final String TAG = "ReviewDTOConverter";

    @Override
    public List<Review> convert(ReviewListDTO reviewListDTO) {
        List<Review> reviewList = new ArrayList<>();

        try {

            List<ReviewDTO> results = reviewListDTO.results;
            for (ReviewDTO item : results) {
                Review review = new Builder()
                    .author(item.author)
                    .content(item.content)
                    .id(item.id)
                    .url(item.url)
                    .build();

                reviewList.add(review);
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return reviewList;
    }
}
