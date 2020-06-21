package com.ravitej.awesomemovies.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ravitej.awesomemovies.domainmodel.Movie;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMovie(Movie movie);

    @Query("SELECT * FROM movie_table")
    Observable<List<Movie>> getAllFavoriteMovies();

    @Query("DELETE FROM movie_table WHERE movie_id = :movieId")
    Completable deleteMovie(int movieId);
}
