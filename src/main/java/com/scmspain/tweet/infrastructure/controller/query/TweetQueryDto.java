package com.scmspain.tweet.infrastructure.controller.query;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class TweetQueryDto {

    private Long id;

    private String publisher;

    private String tweet;

    private Long pre2015MigrationStatus;

    public TweetQueryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Long getPre2015MigrationStatus() {
        return pre2015MigrationStatus;
    }

    public void setPre2015MigrationStatus(Long pre2015MigrationStatus) {
        this.pre2015MigrationStatus = pre2015MigrationStatus;
    }
}

