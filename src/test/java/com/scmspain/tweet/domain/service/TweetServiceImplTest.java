package com.scmspain.tweet.domain.service;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.domain.exception.TweetNotFoundException;
import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.domain.validation.Validator;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceImplTest {
    private static Long ID = 1L;

    @Mock
    private TweetRepository repository;

    @Mock
    private Validator<Tweet> validator;

    @Mock
    private TweetMetricsService metricsService;


    private TweetService tweetServiceImpl;

    @Before
    public void setUp() throws Exception {
        this.tweetServiceImpl = new TweetServiceImpl(repository, validator, metricsService);
    }

    @Test
    public void shouldInsertANewTweet() throws Exception {
        tweetServiceImpl.publishTweet("Guybrush Threepwood", "I am Guybrush Threepwood, mighty pirate.");
        verify(repository).persist(any(Tweet.class));
        verify(metricsService, times(1)).publishedTweets();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenValidationErrorIsThrown() throws Exception {
        doThrow(ValidationException.class).when(validator).validate(any());
        tweetServiceImpl.publishTweet("Pirate", "LeChuck? He's the guy that went to the Governor's for dinner and never wanted to leave. He fell for her in a big way, but she told him to drop dead. So he did. Then things really got ugly.");
        verify(metricsService, times(0)).publishedTweets();
    }

    @Test
    public void shouldInsertTweetsOf140Characters() {
        tweetServiceImpl.publishTweet("LeChuck","01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        verify(repository).persist(any(Tweet.class));
        verify(metricsService, times(1)).publishedTweets();
    }

    @Test
    public void shouldDiscardTweet() {
        Tweet tweet = new Tweet();
        when(repository.findById(ID)).thenReturn(tweet);
        tweetServiceImpl.discardTweet(ID);
        assertThat(tweet.getDiscarded()).isEqualTo(true);
        assertThat(tweet.getDiscardedDate()).isNotNull();
        verify(repository).persist(any(Tweet.class));
        verify(metricsService, times(1)).discardedTweets();
    }

    @Test
    public void shouldNotUpdateDiscardedDateOfAlreadyDiscardedTweet() {
        Date now = new Date();
        Tweet tweet = new Tweet();
        tweet.setDiscarded(true);
        tweet.setDiscardedDate(now);
        when(repository.findById(ID)).thenReturn(tweet);
        tweetServiceImpl.discardTweet(ID);
        assertThat(tweet.getDiscarded()).isEqualTo(true);
        assertThat(tweet.getDiscardedDate()).isEqualTo(now);
        verify(repository, times(0)).persist(any(Tweet.class));
        verify(metricsService, times(0)).discardedTweets();
    }

    @Test(expected = TweetNotFoundException.class)
    public void shouldThrowExceptionWhenDiscardingNotExistantTweet() {
        when(repository.findById(ID)).thenReturn(null);
        tweetServiceImpl.discardTweet(ID);
        verify(metricsService, times(0)).discardedTweets();
    }

    @Test
    public void shouldListAllPublisherTweets() {
        Tweet tweet1 = new Tweet();
        Tweet tweet2 = new Tweet();
        when(repository.findAllPublished()).thenReturn(Arrays.asList(tweet1, tweet2));
        List<Tweet> result = tweetServiceImpl.listAllPublishedTweets();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(tweet1, tweet2);
        verify(metricsService, times(1)).listPublishedTweets();
    }

    @Test
    public void shouldListAllDiscardedTweets() {
        Tweet tweet1 = new Tweet();
        Tweet tweet2 = new Tweet();
        when(repository.findAllDiscarded()).thenReturn(Arrays.asList(tweet1, tweet2));
        List<Tweet> result = tweetServiceImpl.listAllDiscardedTweets();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(tweet1, tweet2);
        verify(metricsService, times(1)).listDiscardedTweets();
    }
}
