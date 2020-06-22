package com.ravitej.awesomemovies.domainmodel;

public class Review {

    private String id;
    private String author;
    private String content;
    private String url;

    public Review(Builder builder) {
        this.id = builder.id;
        this.author = builder.author;
        this.content = builder.content;
        this.url = builder.url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {

        private String id;
        private String author;
        private String content;
        private String url;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
