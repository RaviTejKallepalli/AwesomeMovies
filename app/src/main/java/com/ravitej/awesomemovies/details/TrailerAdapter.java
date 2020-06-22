package com.ravitej.awesomemovies.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ravitej.awesomemovies.R;
import com.ravitej.awesomemovies.databinding.TrailerItemLayoutBinding;
import com.ravitej.awesomemovies.details.TrailerAdapter.ViewHolder;
import com.ravitej.awesomemovies.domainmodel.Trailer;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Trailer> trailers;
    private OnTrailerClickListener listener;
    private TrailerItemLayoutBinding binding;

    public TrailerAdapter(List<Trailer> trailers, OnTrailerClickListener listener) {
        this.trailers = trailers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.trailer_item_layout, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trailerName.setText(trailers.get(position).getTrailerName());
        holder.itemView.setOnClickListener(v -> listener.onClick(trailers.get(position).getKey()));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public void updateItems(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView trailerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerName = binding.trailerName;
        }
    }

    interface OnTrailerClickListener {

        void onClick(String url);
    }
}
