package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.entities.Tweet;
import javax.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

public class TweetPublisherValidatorTest {

  private Validator<Tweet> validator;

  @Before
  public void setUp() throws Exception {
    this.validator = new TweetPublisherValidator();
  }

  @Test
  public void shouldValidateNotEmptyPublisher() {
    Tweet tweet = new Tweet();
    tweet.setPublisher("MonkeyIslandPublisher");
    validator.validate(tweet);
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowValidationExceptionIfPublisherIsNull() {
    Tweet tweet = new Tweet();
    tweet.setPublisher(null);
    validator.validate(tweet);
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowValidationExceptionIfPublisherIsEmpty() {
    Tweet tweet = new Tweet();
    tweet.setPublisher("");
    validator.validate(tweet);
  }
}
