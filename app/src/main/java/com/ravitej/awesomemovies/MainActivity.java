package com.ravitej.awesomemovies;

import static com.ravitej.awesomemovies.MovieDetailsActivity.MOVIE_ITEM_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.ravitej.awesomemovies.MovieAdapter.OnMovieClickListener;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.repository.MovieRepository;
import com.ravitej.awesomemovies.repository.impl.MovieRepositoryImpl;
import java.util.ArrayList;

public class MainActivity
    extends AppCompatActivity
    implements OnMovieClickListener {

    private static final int GRID_SPAN_COUNT = 2;
    private static final String TAG = "MainActivity";

    private RecyclerView movies_rv;
    private MovieAdapter movieAdapter;
    private MovieRepository movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        fetchData();
    }

    private void initRecyclerView() {
        movies_rv = findViewById(R.id.movies_rv);
        movieAdapter = new MovieAdapter(this, new ArrayList<>());

        LayoutManager layoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        movies_rv.setLayoutManager(layoutManager);

        movies_rv.setAdapter(movieAdapter);
    }

    private void fetchData() {
        movieRepository = new MovieRepositoryImpl();
        fetchPopularMovies();
    }

    private void fetchPopularMovies() {
        movieRepository.fetchPopularMovies()
            .subscribe(
                movieDTO -> movieAdapter.updateList(movieDTO),
                throwable -> Log.e(TAG, "Error" + throwable.getMessage())
            );
    }

    private void fetchTopRatedMovies() {
        movieRepository.fetchTopRatedMovies()
            .subscribe(
                movieDTO -> movieAdapter.updateList(movieDTO),
                throwable -> Log.e(TAG, "Error" + throwable.getMessage())
            );
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
                fetchPopularMovies();
                break;
            case R.id.sortByTopRated:
                fetchTopRatedMovies();
                break;
        }

        return true;
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ITEM_EXTRA, movie);
        startActivity(intent);
    }
}