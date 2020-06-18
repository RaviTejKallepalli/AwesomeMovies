package com.ravitej.awesomemovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ravitej.awesomemovies.MovieAdapter.MovieViewHolder;
import com.ravitej.awesomemovies.domainmodel.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private OnMovieClickListener listener;
    private List<Movie> movieList;

    public MovieAdapter(OnMovieClickListener listener, List<Movie> movieList) {
        this.listener = listener;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
            .inflate(
                R.layout.movie_list_item,
                parent,
                false
            );

        return new MovieViewHolder(view);
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

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster_iv;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_iv = itemView.findViewById(R.id.movie_poster_iv);
        }
    }

    public interface OnMovieClickListener {

        void onClick(Movie movie);
    }
}
