package com.ravitej.awesomemovies.domainmodel;

public class Trailer {

    private String trailerId;
    private String key;
    private String trailerName;
    private String site;
    private String type;
    private int size;

    public Trailer(Builder builder) {
        this.trailerId = builder.trailerId;
        this.key = builder.key;
        this.trailerName = builder.trailerName;
        this.site = builder.site;
        this.type = builder.type;
        this.size = builder.size;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public String getKey() {
        return key;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public static class Builder {

        private String trailerId;
        private String key;
        private String trailerName;
        private String site;
        private String type;
        private int size;

        public Builder trailerId(String trailerId) {
            this.trailerId = trailerId;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder trailerName(String trailerName) {
            this.trailerName = trailerName;
            return this;
        }

        public Builder site(String site) {
            this.site = site;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Trailer build() {
            return new Trailer(this);
        }
    }
}
