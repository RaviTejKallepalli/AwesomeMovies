package com.ravitej.awesomemovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_ITEM_EXTRA = "movie_item";

    private TextView synopsis;
    private TextView userRating;
    private TextView releaseDate;
    private ImageView imageView;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initViews();

        getBundleData();
        setTitle(movie.getOriginalTitle());
        updateViews(movie);
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
}