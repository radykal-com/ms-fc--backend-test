package com.scmspain.services;

import com.scmspain.entities.Tweet;
import com.scmspain.exception.TweetNotFoundException;
import com.scmspain.validation.Validator;
import java.util.Date;
import javax.validation.ValidationException;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TweetServiceImpl implements TweetService {
    private Validator<Tweet> validator;
    private EntityManager entityManager;
    private MetricWriter metricWriter;

    public TweetServiceImpl(Validator<Tweet> validator, EntityManager entityManager,  MetricWriter metricWriter) {
        this.validator = validator;
        this.entityManager = entityManager;
        this.metricWriter = metricWriter;
    }

    @Override
    public void publishTweet(String publisher, String text) {
        Tweet tweet = new Tweet();
        tweet.setTweet(text);
        tweet.setPublisher(publisher);
        tweet.setDiscarded(false);
        validateTweet(tweet);
        this.metricWriter.increment(new Delta<Number>("published-tweets", 1));
        this.entityManager.persist(tweet);
    }

    @Override
    public Tweet getTweet(Long id) {
      return this.entityManager.find(Tweet.class, id);
    }

    @Override
    public List<Tweet> listAllTweets() {
        List<Tweet> result = new ArrayList<Tweet>();
        this.metricWriter.increment(new Delta<Number>("times-queried-tweets", 1));
        TypedQuery<Long> query = this.entityManager.createQuery("SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 AND discarded=false ORDER BY id DESC", Long.class);
        List<Long> ids = query.getResultList();
        for (Long id : ids) {
            result.add(getTweet(id));
        }
        return result;
    }

    @Override
    public void discardTweet(Long id) throws TweetNotFoundException {
        Tweet tweet = getTweet(id);
        if (tweet == null) {
            throw new TweetNotFoundException();
        }
        if (!tweet.getDiscarded()) {
          tweet.setDiscarded(true);
          tweet.setDiscardedDate(new Date());
          this.entityManager.persist(tweet);
        }
    }

    @Override
    public List<Tweet> listAllDiscardedTweets() {
        List<Tweet> result = new ArrayList<Tweet>();
        this.metricWriter.increment(new Delta<Number>("times-queried-tweets", 1));
        TypedQuery<Long> query = this.entityManager.createQuery("SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 AND discarded=true ORDER BY discardedDate DESC", Long.class);
        List<Long> ids = query.getResultList();
        for (Long id : ids) {
            result.add(getTweet(id));
        }
        return result;
    }

    private void validateTweet(Tweet tweet) {
        try {
            this.validator.validate(tweet);
        } catch (ValidationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
