package com.ravitej.awesomemovies.mainactivity;

import static com.ravitej.awesomemovies.details.MovieDetailsActivity.MOVIE_ITEM_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.ravitej.awesomemovies.R;
import com.ravitej.awesomemovies.databinding.ActivityMainBinding;
import com.ravitej.awesomemovies.details.MovieDetailsActivity;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.mainactivity.MovieAdapter.OnMovieClickListener;
import java.util.ArrayList;

public class MainActivity
    extends AppCompatActivity
    implements OnMovieClickListener {

    private static final String TAG = "MainActivity";

    private MovieAdapter movieAdapter;
    private MainViewModel viewModel;
    private MainViewModelFactory mainViewModelFactory;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initRecyclerView();

        mainViewModelFactory = new MainViewModelFactory(this.getApplication());
        viewModel = new ViewModelProvider(this, mainViewModelFactory).get(MainViewModel.class);

        initViewModel();
    }

    private void initRecyclerView() {
        movieAdapter = new MovieAdapter(this, new ArrayList<>());

        LayoutManager layoutManager = new GridLayoutManager(
            this,
            getResources().getInteger(R.integer.grid_span)
        );
        binding.moviesRv.setLayoutManager(layoutManager);

        binding.moviesRv.setAdapter(movieAdapter);
    }

    private void initViewModel() {
        viewModel.getMovieListLiveData()
            .observe(this, movieList -> {
                movieAdapter.updateList(movieList);
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.sortByPopular:
                viewModel.fetchPopularMovies();
                return true;
            case R.id.sortByTopRated:
                viewModel.fetchTopRatedMovies();
                return true;
            case R.id.favoriteMovies:
                viewModel.fetchFavoriteMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ITEM_EXTRA, movie);
        startActivity(intent);
    }
}