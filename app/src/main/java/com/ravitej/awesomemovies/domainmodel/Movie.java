package com.ravitej.awesomemovies.domainmodel;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "synopsis")
    private String synopsis;

    @ColumnInfo(name = "user_rating")
    private String userRating;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "favorite")
    private boolean isFavorite;

    public Movie(int movieId, String originalTitle, String posterPath, String synopsis,
        String userRating, String releaseDate, boolean favorite) {
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.isFavorite = favorite;
    }

    @Ignore
    public Movie(Builder builder) {
        this.originalTitle = builder.originalTitle;
        this.posterPath = builder.posterPath;
        this.synopsis = builder.synopsis;
        this.userRating = builder.userRating;
        this.releaseDate = builder.releaseDate;
        this.movieId = builder.movieId;
        this.isFavorite = builder.isFavorite;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public static class Builder {

        private String originalTitle;
        private String posterPath;
        private String synopsis;
        private String userRating;
        private String releaseDate;
        private int movieId;
        private boolean isFavorite;

        public Builder originalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public Builder posterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Builder synopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public Builder userRating(String userRating) {
            this.userRating = userRating;
            return this;
        }

        public Builder releaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder movieId(int movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder isfavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalTitle);
        dest.writeString(this.posterPath);
        dest.writeString(this.synopsis);
        dest.writeString(this.userRating);
        dest.writeString(this.releaseDate);
        dest.writeInt(this.movieId);
        dest.writeInt(this.isFavorite ? 1 : 0);
    }

    protected Movie(Parcel in) {
        this.originalTitle = in.readString();
        this.posterPath = in.readString();
        this.synopsis = in.readString();
        this.userRating = in.readString();
        this.releaseDate = in.readString();
        this.movieId = in.readInt();
        this.isFavorite = (in.readInt() == 1);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
