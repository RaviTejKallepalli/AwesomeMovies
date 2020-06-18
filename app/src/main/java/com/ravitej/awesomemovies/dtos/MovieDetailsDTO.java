package com.ravitej.awesomemovies.dtos;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class MovieDetailsDTO {

    @SerializedName("poster_path")
    @Nullable
    public String poster_path;

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("id")
    public int id;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("title")
    public String title;

    @Nullable
    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("popularity")
    public String popularity;

    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("video")
    public boolean hasVideo;

    @SerializedName("vote_average")
    public String voteAverage;
}
