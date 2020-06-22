package com.ravitej.awesomemovies.dtos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TrailerWrapperDTO {

    @SerializedName("id")
    public int id;

    @SerializedName("results")
    public List<TrailerDTO> results;

    public class TrailerDTO {

        @SerializedName("id")
        public String trailerId;

        @SerializedName("key")
        public String key;

        @SerializedName("name")
        public String trailerName;

        @SerializedName("site")
        public String site;

        @SerializedName("type")
        public String type;

        @SerializedName("size")
        public int size;
    }
}
