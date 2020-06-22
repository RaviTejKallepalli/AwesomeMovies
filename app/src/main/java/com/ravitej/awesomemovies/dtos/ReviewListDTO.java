package com.ravitej.awesomemovies.dtos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReviewListDTO {

    @SerializedName("id")
    public int id;

    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public List<ReviewDTO> results;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    public class ReviewDTO {

        @SerializedName("id")
        public String id;

        @SerializedName("author")
        public String author;

        @SerializedName("content")
        public String content;

        @SerializedName("url")
        public String url;
    }
}
