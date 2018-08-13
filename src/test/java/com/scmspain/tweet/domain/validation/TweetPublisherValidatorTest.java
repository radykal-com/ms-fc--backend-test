package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.model.Tweet;
import javax.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

public class TweetPublisherValidatorTest {

  private Validator<Tweet> validator;

  @Before
  public void setUp() {
    this.validator = new TweetPublisherValidator();
  }

  @Test
  public void shouldValidateNotEmptyPublisher() {
    Tweet tweet = buildTweet("LucasArts");
    validator.validate(tweet);
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowValidationExceptionIfPublisherIsNull() {
    Tweet tweet = buildTweet(null);
    validator.validate(tweet);
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowValidationExceptionIfPublisherIsEmpty() {
    Tweet tweet = buildTweet("");
    validator.validate(tweet);
  }

  private Tweet buildTweet(String publisher) {
    Tweet tweet = new Tweet();
    tweet.setPublisher(publisher);
    return tweet;
  }
}
