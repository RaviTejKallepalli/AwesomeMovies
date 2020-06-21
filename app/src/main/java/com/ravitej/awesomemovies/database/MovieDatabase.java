package com.ravitej.awesomemovies.database;

import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.ravitej.awesomemovies.dao.MovieDao;
import com.ravitej.awesomemovies.domainmodel.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static final String TAG = MovieDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "movie_database";
    private static MovieDatabase sInstance;


    public static MovieDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MovieDatabase.class,
                    DATABASE_NAME
                ).build();
            }
        }
        return sInstance;
    }
}
