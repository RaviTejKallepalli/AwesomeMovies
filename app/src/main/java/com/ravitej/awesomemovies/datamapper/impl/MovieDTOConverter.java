package com.ravitej.awesomemovies.datamapper.impl;

import static com.ravitej.awesomemovies.network.NetworkUtils.BASE_IMAGE_URL;

import android.util.Log;
import com.ravitej.awesomemovies.datamapper.DataMapper;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.domainmodel.Movie.Builder;
import com.ravitej.awesomemovies.dtos.MovieDTO;
import com.ravitej.awesomemovies.dtos.MovieDetailsDTO;
import java.util.ArrayList;
import java.util.List;

public class MovieDTOConverter implements DataMapper<MovieDTO, List<Movie>> {

    private static final String TAG = "MovieDTOConverter";

    @Override
    public List<Movie> convert(MovieDTO movieDTO) {
        List<Movie> movies = new ArrayList<>();

        try {
            List<MovieDetailsDTO> list = movieDTO.movieList;
            for (MovieDetailsDTO movieItem : list) {

                Movie movie = new Builder()
                    .originalTitle(movieItem.originalTitle)
                    .posterPath(buildPosterPath(movieItem.poster_path))
                    .releaseDate(movieItem.release_date)
                    .synopsis(movieItem.overview)
                    .userRating(movieItem.voteAverage)
                    .build();

                movies.add(movie);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return movies;
    }

    private String buildPosterPath(String posterPath) {
        return BASE_IMAGE_URL + posterPath;
    }
}
