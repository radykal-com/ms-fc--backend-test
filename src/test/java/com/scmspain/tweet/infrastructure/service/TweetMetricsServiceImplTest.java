package com.scmspain.tweet.infrastructure.service;

import com.scmspain.tweet.domain.service.TweetMetricsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TweetMetricsServiceImplTest {

    private TweetMetricsService tweetMetricsService;

    @Mock
    MetricWriter metricWriter;

    @Before
    public void setUp() {
        tweetMetricsService = new TweetMetricsServiceImpl(metricWriter);
    }

    @Test
    public void shouldTrackPublishedTweets() {
        tweetMetricsService.publishedTweets();
        verify(metricWriter, times(1)).increment(any(Delta.class));
    }

    @Test
    public void shouldTrackListPublishedTweets() {
        tweetMetricsService.listPublishedTweets();
        verify(metricWriter, times(1)).increment(any(Delta.class));
    }

    @Test
    public void shouldTrackDiscardedTweets() {
        tweetMetricsService.discardedTweets();
        verify(metricWriter, times(1)).increment(any(Delta.class));
    }

    @Test
    public void shouldListDiscardedTweets() {
        tweetMetricsService.listDiscardedTweets();
        verify(metricWriter, times(1)).increment(any(Delta.class));
    }
}
