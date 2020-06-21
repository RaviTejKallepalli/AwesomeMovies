package com.ravitej.awesomemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.ravitej.awesomemovies.dao.MovieDao;
import com.ravitej.awesomemovies.database.MovieDatabase;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import com.ravitej.awesomemovies.utils.impl.RxSchedularsProviderImpl;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_ITEM_EXTRA = "movie_item";
    private static final String TAG = "MovieDetailsActivity";

    private TextView synopsis;
    private TextView userRating;
    private TextView releaseDate;
    private ImageView imageView;
    private Movie movie;

    private MovieDatabase movieDatabase;
    private MovieDao movieDao;
    private RxSchedularProvider rxSchedularProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initViews();

        getBundleData();
        setTitle(movie.getOriginalTitle());
        updateViews(movie);
        init();
    }

    private void getBundleData() {
        Intent intent = getIntent();

        if (intent == null) {
            closeOnError();
        }

        if ((movie = intent.getParcelableExtra(MOVIE_ITEM_EXTRA)) == null) {
            closeOnError();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.not_available), Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        synopsis = findViewById(R.id.synopsis_tv);
        userRating = findViewById(R.id.user_rating_tv);
        releaseDate = findViewById(R.id.release_date_tv);
        imageView = findViewById(R.id.movie_poster_iv);
    }

    private void updateViews(Movie movie) {
        Picasso.get()
            .load(movie.getPosterPath())
            .into(imageView);

        synopsis.setText(movie.getSynopsis());
        userRating.setText(movie.getUserRating());
        releaseDate.setText(movie.getReleaseDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //FIXME: InConsistant when launching the movie details with favorite film
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite) {
            if (!movie.isFavorite()) {
                addMovieToFavorite();
            } else {
                deleteFromFavorite();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteFromFavorite() {
        movie.setFavorite(false);
        //TODO: Is it good way to do this?
        movieDao.deleteMovie(movie.getMovieId())
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular())
            .subscribe(this::invalidateOptionsMenu);
    }

    private void addMovieToFavorite() {
        movie.setFavorite(true);
        //TODO: Is it good way to do this?
        movieDao.insertFavoriteMovie(movie)
            .observeOn(rxSchedularProvider.uiSchedular())
            .subscribeOn(rxSchedularProvider.diskSchedular())
            .subscribe(this::invalidateOptionsMenu);
    }

    private void init() {
        movieDatabase = MovieDatabase.getsInstance(this.getApplication());
        movieDao = movieDatabase.movieDao();
        rxSchedularProvider = RxSchedularsProviderImpl.getInstance();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (movie.isFavorite()) {
            MenuItem favItem = menu.findItem(R.id.favorite);
            // set your desired icon here based on a flag if you like
            favItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_fav_filled_white));
        }

        return super.onPrepareOptionsMenu(menu);
    }
}