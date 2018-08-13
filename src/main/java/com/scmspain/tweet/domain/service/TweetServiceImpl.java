package com.scmspain.tweet.domain.service;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.domain.exception.TweetNotFoundException;
import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.domain.validation.Validator;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

    private TweetRepository repository;
    private Validator<Tweet> validator;
    private TweetMetricsService metricsService;

    public TweetServiceImpl(TweetRepository repository, Validator<Tweet> validator, TweetMetricsService metricsService) {
        this.repository = repository;
        this.validator = validator;
        this.metricsService = metricsService;
    }

    @Override
    public void publishTweet(String publisher, String content) {
        Tweet tweet = new Tweet();
        tweet.setPublisher(publisher);
        tweet.setContent(content);
        validateTweet(tweet);
        metricsService.publishedTweets();
        repository.persist(tweet);
    }

    @Override
    public List<Tweet> listAllPublishedTweets() {
        metricsService.listPublishedTweets();
        return repository.findAllPublished();
    }

    @Override
    public void discardTweet(Long id) throws TweetNotFoundException {
        Tweet tweet = repository.findById(id);
        if (tweet == null) {
            throw new TweetNotFoundException(String.format("Could not find any Tweet with id %s",id));
        }
        if (!tweet.getDiscarded()) {
            tweet.setDiscarded(true);
            tweet.setDiscardedDate(new Date());
            repository.persist(tweet);
            metricsService.discardedTweets();
        }
    }

    @Override
    public List<Tweet> listAllDiscardedTweets() {
        metricsService.listDiscardedTweets();
        return repository.findAllDiscarded();
    }

    private void validateTweet(Tweet tweet) {
        try {
            this.validator.validate(tweet);
        } catch (ValidationException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
