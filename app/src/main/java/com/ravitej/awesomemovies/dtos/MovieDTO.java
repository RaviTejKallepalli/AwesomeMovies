package com.ravitej.awesomemovies.dtos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieDTO {

    @SerializedName("page")
    public int page;

    @SerializedName("total_results")
    public int total_results;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("results")
    public List<MovieDetailsDTO> movieList;
}
