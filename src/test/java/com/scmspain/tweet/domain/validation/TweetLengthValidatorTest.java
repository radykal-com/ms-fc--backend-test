package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.entities.Tweet;
import javax.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

public class TweetLengthValidatorTest {

  private Validator<Tweet> validator;

  @Before
  public void setUp() throws Exception {
    this.validator = new TweetLengthValidator();
  }

  @Test
  public void shouldValidateValidTweetLengthWithoutLink() {
    Tweet tweet = new Tweet();
    tweet.setTweet("Short tweet");
    validator.validate(tweet);
  }

  @Test
  public void shouldValidateValid140CharsTweet() {
    Tweet tweet = new Tweet();
    tweet.setTweet("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
    validator.validate(tweet);
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowValidationExceptionIfLongerThan140Chars() {
    Tweet tweet = new Tweet();
    tweet.setTweet("101234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
    validator.validate(tweet);
  }

  @Test
  public void shouldValidateIfValidLengthWithLinkAtEnding() {
    Tweet tweet = new Tweet();
    tweet.setTweet("012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678 http://a.link.com");
    validator.validate(tweet);
  }

  @Test
  public void shouldValidateIfValidLengthWithLinkAtStart() {
    Tweet tweet = new Tweet();
    tweet.setTweet("http://a.link.com 012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678");
    validator.validate(tweet);
  }

  @Test
  public void shouldValidateIfValidLengthWithLinkInTheMiddle() {
    Tweet tweet = new Tweet();
    tweet.setTweet("0123456789012345678901234567890123456789012345678901234567890123 https://a.link.com 4567890123456789012345678901234567890123456789012345678901234567");
    validator.validate(tweet);
  }

  @Test
  public void shouldValidateIfValidLengthWithMultipleLinks() {
    Tweet tweet = new Tweet();
    tweet.setTweet("https://another.link.com?with=querystring&a=1 128876862382783242348738272386543210987654321789012345678901234567890123 https://a.link.com 4567890123456789012345678901234567890123456789012345678901234567 https://a.link.com");
    validator.validate(tweet);
  }
}
