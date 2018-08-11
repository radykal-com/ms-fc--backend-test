package com.scmspain.services;

import com.scmspain.entities.Tweet;
import com.scmspain.exception.TweetNotFoundException;
import com.scmspain.validation.Validator;
import java.util.Date;
import javax.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import javax.persistence.EntityManager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class TweetServiceImplTest {
    private static Long ID = 1L;

    private EntityManager entityManager;
    private MetricWriter metricWriter;
    private Validator<Tweet> validator;
    private TweetServiceImpl tweetServiceImpl;

    @Before
    public void setUp() throws Exception {
        this.validator = mock(Validator.class);
        this.entityManager = mock(EntityManager.class);
        this.metricWriter = mock(MetricWriter.class);

        this.tweetServiceImpl = new TweetServiceImpl(validator, entityManager, metricWriter);
    }

    @Test
    public void shouldInsertANewTweet() throws Exception {
        tweetServiceImpl.publishTweet("Guybrush Threepwood", "I am Guybrush Threepwood, mighty pirate.");
        verify(entityManager).persist(any(Tweet.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenValidationErrorIsThrown() throws Exception {
        doThrow(ValidationException.class).when(validator).validate(any());
        tweetServiceImpl.publishTweet("Pirate", "LeChuck? He's the guy that went to the Governor's for dinner and never wanted to leave. He fell for her in a big way, but she told him to drop dead. So he did. Then things really got ugly.");
    }

    @Test
    public void shouldInsertTweetsOf140Characters() {
        tweetServiceImpl.publishTweet("LeChuck","01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        verify(entityManager).persist(any(Tweet.class));
    }

    @Test
    public void shouldDiscardTweet() {
        Tweet tweet = new Tweet();
        tweet.setDiscarded(false);
        when(entityManager.find(Tweet.class, ID)).thenReturn(tweet);
        tweetServiceImpl.discardTweet(ID);
        assertThat(tweet.getDiscarded()).isEqualTo(true);
        assertThat(tweet.getDiscardedDate()).isNotNull();
        verify(entityManager).persist(any(Tweet.class));
    }

    @Test
    public void shouldNotUpdateDiscardedDateOfAlreadyDiscardedTweet() {
        Date now = new Date();
        Tweet tweet = new Tweet();
        tweet.setDiscarded(true);
        tweet.setDiscardedDate(now);
        when(entityManager.find(Tweet.class, ID)).thenReturn(tweet);
        tweetServiceImpl.discardTweet(ID);
        assertThat(tweet.getDiscarded()).isEqualTo(true);
        assertThat(tweet.getDiscardedDate()).isEqualTo(now);
        verify(entityManager, times(0)).persist(any(Tweet.class));
    }

    @Test(expected = TweetNotFoundException.class)
    public void shouldThrowExceptionWhenDiscardingNotExistantTweet() {
        when(entityManager.find(Tweet.class, ID)).thenReturn(null);
        tweetServiceImpl.discardTweet(ID);
    }
}
