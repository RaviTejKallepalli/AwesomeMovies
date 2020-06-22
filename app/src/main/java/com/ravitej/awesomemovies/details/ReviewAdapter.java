package com.ravitej.awesomemovies.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ravitej.awesomemovies.R;
import com.ravitej.awesomemovies.databinding.ReviewItemLayoutBinding;
import com.ravitej.awesomemovies.details.ReviewAdapter.ViewHolder;
import com.ravitej.awesomemovies.domainmodel.Review;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Review> list;
    private ReviewItemLayoutBinding binding;

    public ReviewAdapter(List<Review> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.review_item_layout, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contentTextView.setText(list.get(position).getContent());
        holder.authorTextView.setText(list.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateListItem(List<Review> results) {
        this.list = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView authorTextView;
        TextView contentTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTextView = binding.author;
            contentTextView = binding.content;
        }
    }
}
