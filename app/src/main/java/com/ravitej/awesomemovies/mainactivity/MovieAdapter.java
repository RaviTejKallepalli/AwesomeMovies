package com.ravitej.awesomemovies.mainactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ravitej.awesomemovies.R;
import com.ravitej.awesomemovies.databinding.MovieListItemBinding;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.ravitej.awesomemovies.mainactivity.MovieAdapter.MovieViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private OnMovieClickListener listener;
    private List<Movie> movieList;
    private MovieListItemBinding binding;

    public MovieAdapter(OnMovieClickListener listener, List<Movie> movieList) {
        this.listener = listener;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_list_item,
            parent,
            false
        );

        return new MovieViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get()
            .load(movieList.get(position).getPosterPath())
            .into(holder.poster_iv);

        holder.itemView.setOnClickListener(v -> listener.onClick(movieList.get(position)));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster_iv;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_iv = binding.moviePosterIv;
        }
    }

    public interface OnMovieClickListener {

        void onClick(Movie movie);
    }
}
