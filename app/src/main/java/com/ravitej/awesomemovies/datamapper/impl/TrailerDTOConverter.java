package com.ravitej.awesomemovies.datamapper.impl;

import android.util.Log;
import com.ravitej.awesomemovies.datamapper.DataMapper;
import com.ravitej.awesomemovies.domainmodel.Trailer;
import com.ravitej.awesomemovies.domainmodel.Trailer.Builder;
import com.ravitej.awesomemovies.dtos.TrailerWrapperDTO;
import com.ravitej.awesomemovies.dtos.TrailerWrapperDTO.TrailerDTO;
import java.util.ArrayList;
import java.util.List;

public class TrailerDTOConverter implements DataMapper<TrailerWrapperDTO, List<Trailer>> {

    private static final String TAG = "TrailerDTOConverter";

    @Override
    public List<Trailer> convert(TrailerWrapperDTO trailerWrapperDTO) {
        List<Trailer> trailers = new ArrayList<>();

        try {
            List<TrailerDTO> results = trailerWrapperDTO.results;

            for (TrailerDTO item : results) {
                Trailer trailer = new Builder()
                    .trailerId(item.trailerId)
                    .trailerName(item.trailerName)
                    .key(item.key)
                    .site(item.site)
                    .size(item.size)
                    .type(item.type)
                    .build();

                trailers.add(trailer);
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return trailers;
    }
}
