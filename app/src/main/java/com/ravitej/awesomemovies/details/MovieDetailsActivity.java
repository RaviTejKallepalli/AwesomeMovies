package com.ravitej.awesomemovies.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.ravitej.awesomemovies.R;
import com.ravitej.awesomemovies.databinding.ActivityMovieDetailsBinding;
import com.ravitej.awesomemovies.details.TrailerAdapter.OnTrailerClickListener;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity implements OnTrailerClickListener {

    public static final String MOVIE_ITEM_EXTRA = "movie_item";
    private static final String TAG = "MovieDetailsActivity";

    private Movie movie;
    private DetailsViewModel viewModel;
    private DetailsViewModelFactory viewModelFactory;
    private ActivityMovieDetailsBinding binding;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getBundleData();
        setTitle(movie.getOriginalTitle());
        initViewModel();

        updateViews(movie);
        initRecyclerView();
    }

    private void initRecyclerView() {
        trailerAdapter = new TrailerAdapter(new ArrayList<>(), this);
        LayoutManager trailerLayoutManager = new LinearLayoutManager(this);
        binding.trailersRv.setLayoutManager(trailerLayoutManager);
        binding.trailersRv.setAdapter(trailerAdapter);
        binding.trailersRv.setHasFixedSize(true);
        binding.trailersRv.setNestedScrollingEnabled(false);

        LayoutManager reviewLayoutManager = new LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false);
        reviewAdapter = new ReviewAdapter(new ArrayList<>());
        binding.reviewsRv.setLayoutManager(reviewLayoutManager);
        binding.reviewsRv.setAdapter(reviewAdapter);
        binding.reviewsRv.setHasFixedSize(true);
        binding.reviewsRv.setNestedScrollingEnabled(false);
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

    private void initViewModel() {
        viewModelFactory = new DetailsViewModelFactory(this.getApplication(), movie);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(DetailsViewModel.class);

//        viewModel.init(movie);
        viewModel.getFavoriteLiveData()
            .observe(
                this,
                value -> {
                    movie.setFavorite(value);
                    invalidateOptionsMenu();
                }
            );

        viewModel.getTrailersLiveData()
            .observe(
                this,
                trailers -> trailerAdapter.updateItems(trailers)
            );

        viewModel.getReviewsLiveData()
            .observe(
                this,
                results -> {
                    reviewAdapter.updateListItem(results);
                }
            );
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.not_available), Toast.LENGTH_SHORT).show();
    }

    private void updateViews(Movie movie) {
        Picasso.get()
            .load(movie.getPosterPath())
            .into(binding.moviePosterIv);

        binding.synopsisTv.setText(movie.getSynopsis());
        binding.userRatingTv.setText(movie.getUserRating());
        binding.releaseDateTv.setText(movie.getReleaseDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.favorite:
                if (!movie.isFavorite()) {
                    viewModel.addMovieToFavorite();
                } else {
                    viewModel.deleteFromFavorite();
                }
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (movie.isFavorite()) {
            MenuItem favItem = menu.findItem(R.id.favorite);
            favItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_fav_filled_white));
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + url));
        startActivity(intent);
    }
}