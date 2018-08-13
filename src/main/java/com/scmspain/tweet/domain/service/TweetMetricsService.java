package com.scmspain.tweet.domain.service;

public interface TweetMetricsService {

    void publishedTweets();

    void listPublishedTweets();

    void discardedTweets();

    void listDiscardedTweets();
}
