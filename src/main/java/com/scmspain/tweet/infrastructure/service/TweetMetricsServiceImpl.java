package com.scmspain.tweet.infrastructure.service;

import com.scmspain.tweet.domain.service.TweetMetricsService;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

public class TweetMetricsServiceImpl implements TweetMetricsService {

    private static String PUBLISHED = "published-tweets";
    private static String LIST_PUBLISHED = "times-queried-tweets";
    private static String DISCARDED = "discarded-tweet";
    private static String LIST_DISCARDED = "list-queried-discarded-tweets";

    private MetricWriter metricWriter;

    public TweetMetricsServiceImpl(MetricWriter metricWriter) {
        this.metricWriter = metricWriter;
    }

    @Override
    public void publishedTweets() {
        metricWriter.increment(new Delta<>(PUBLISHED, 1));
    }

    @Override
    public void listPublishedTweets() {
        metricWriter.increment(new Delta<>(LIST_PUBLISHED, 1));
    }

    @Override
    public void discardedTweets() {
        metricWriter.increment(new Delta<>(DISCARDED, 1));
    }

    @Override
    public void listDiscardedTweets() {
        metricWriter.increment(new Delta<>(LIST_DISCARDED, 1));
    }
}
